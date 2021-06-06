package com.epam.util.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.epam.util.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ConnectionPool definition class.
 * Implemented using singleton
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ConnectionPool {

    private static final Logger LOGGER
            = LogManager.getLogger(ConnectionPool.class);

    /** Jdbc url */
    private String jdbcUrl;

    /** User name */
    private String jdbcUser;

    /** User password */
    private String jdbcPassword;

    /** Max count of connections */
    private int maxSize;

    /** Timout */
    private int validationTimeout;

    /** Free connections */
    private final Queue<ConnectionWrapper> freeConnections
            = new ConcurrentLinkedQueue<>();

    /** Used connection */
    private final Set<ConnectionWrapper> usedConnections
            = new ConcurrentSkipListSet<>();

    /**
     * Get connection method
     * @return connection (connection wrapper)
     * @throws ConnectionPoolException if pool is overloaded
     */
    public Connection getConnection()
            throws ConnectionPoolException {
        ConnectionWrapper connection = null;
        while(connection == null) {
            connection = freeConnections.poll();
            if(connection != null) {
                boolean isConnectionValid;
                try {
                    isConnectionValid
                            = connection.isValid(validationTimeout);
                } catch(SQLException e) {
                    isConnectionValid = false;
                }
                if(!isConnectionValid) {
                    close(connection);
                    connection = null;
                }
            } else if(usedConnections.size() < maxSize) {
                connection = establishConnection();
                LOGGER.warn("New connection was created.");
            } else {
                LOGGER.error("Connection pool max size exceeded");
                throw new ConnectionPoolException();
            }
        }
        usedConnections.add(connection);
        LOGGER.info("Take connection from pool, " +
                "used connections - {}, free connections - {}",
                usedConnections.size(), freeConnections.size());
        return connection;
    }

    /**
     * Connection pool init method
     * @param jdbcDriver driver name
     * @param jdbcUrl db url
     * @param jdbcUser db user name
     * @param jdbcPassword db user password
     * @param minSize min connections count
     * @param maxSize mxx connections count
     * @param validationTimeout validation timeout
     * @throws ConnectionPoolException if driver is not found
     */
    public synchronized void init(String jdbcDriver, String jdbcUrl,
                                  String jdbcUser,
                                  String jdbcPassword, int minSize,
                                  int maxSize, int validationTimeout)
            throws ConnectionPoolException {
        try {
            Class.forName(jdbcDriver);
            this.jdbcUrl = jdbcUrl;
            this.jdbcUser = jdbcUser;
            this.jdbcPassword = jdbcPassword;
            this.maxSize = maxSize;
            this.validationTimeout = validationTimeout;
            for(int i = 0; i < minSize; i++) {
                freeConnections.add(establishConnection());
            }
            LOGGER.info("Connection pool init success, " +
                    "free connections - {}",
                    freeConnections.size());
        } catch(ClassNotFoundException e) {
            throw new ConnectionPoolException(e);
        }
    }

    /**
     * Destroying
     */
    public void destroy() {
        synchronized (usedConnections) {
            synchronized (freeConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for(ConnectionWrapper connection : usedConnections) {
                    close(connection);
                }
                usedConnections.clear();
                closer.shutdown();
                LOGGER.info(
                        "All connections in pool have been closed");
            }
        }
    }

    /**
     * Free connection method.
     * Puts method to free connections queue
     * @param connection to free up
     */
    void freeConnection(ConnectionWrapper connection)
            throws SQLException {
        try {
            usedConnections.remove(connection);
            connection.clearWarnings();
            freeConnections.add(connection);
            LOGGER.info("Return connection to pool, " +
                    "used connections - {}, free connections - {}",
                    usedConnections.size(), freeConnections.size());
        } catch(SQLException e) {
            close(connection);
            throw e;
        }
    }

    /**
     * Establish connection method
     * @return connection wrapper
     */
    private ConnectionWrapper establishConnection()
            throws ConnectionPoolException {
        try {
            return new ConnectionWrapper(DriverManager.getConnection(
                    jdbcUrl, jdbcUser, jdbcPassword));
        } catch(SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    private static final ExecutorService closer
            = Executors.newSingleThreadExecutor();

    /**
     * Close method (free connection using wrapper)
     * @param connection wrapper to free
     */
    private void close(ConnectionWrapper connection) {
        closer.execute(() -> {
            synchronized (connection) {
                try {
                    connection.getConnection().close();
                }
                catch(SQLException ignored) {}
            }
        });
    }

    /** Default private constructor */
    private ConnectionPool() {}

    /** Connection pool instance */
    private static final ConnectionPool instance = new ConnectionPool();

    /** Get instance singleton method */
    public static ConnectionPool getInstance() {
        return instance;
    }
}
