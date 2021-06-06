package com.epam.controller.listeners;

import com.epam.util.connectionpool.ConnectionPool;
import com.epam.util.connectionpool.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ApplicationStartListener definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ApplicationStartListener
        implements ServletContextListener {

    private static final Logger LOGGER
            = LogManager.getLogger(ApplicationStartListener.class);

    /**
     * Context initializing listener.
     * Initializes connection pool with driver,
     * connecting to DB
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            ConnectionPool.getInstance().init(
                    "com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/" +
                            "hospital_db?useUnicode" +
                            "=true&characterEncoding=UTF-8",
                    "root", "2694", 1, 10, 10);
        } catch(ConnectionPoolException e) {
            LOGGER.fatal("Connection pool init error in {}",
                    ConnectionPool.class.getName(), e);
        }
    }

    /**
     * Context destroyed listener.
     * Destroying connection pool
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
        LOGGER.fatal("Connection pool destroyed.");
    }
}
