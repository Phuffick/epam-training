package com.epam.util.connectionpool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.ShardingKey;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * ConnectionWrapper definition class.
 * Wraps connection
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
class ConnectionWrapper implements Connection,
        Comparable<ConnectionWrapper> {

    /** Connection to wrap */
    private final Connection connection;

    /**
     * Default constructor
     * @param connection
     */
    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get connection (connection wrapper) method
     * @return connection (connection wrapper)
     */
    Connection getConnection() {
        return connection;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        checkConnectionNull();
        return connection.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        checkConnectionNull();
        return connection.isWrapperFor(iface);
    }

    @Override
    public Statement createStatement() throws SQLException {
        checkConnectionNull();
        return connection.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        checkConnectionNull();
        return connection.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit)
            throws SQLException {
        checkConnectionNull();
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        checkConnectionNull();
        return connection.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        checkConnectionNull();
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException {
        checkConnectionNull();
        connection.rollback();
    }

    @Override
    public void close() throws SQLException {
        checkConnectionNull();
        ConnectionPool.getInstance().freeConnection(this);
    }

    @Override
    public boolean isClosed() throws SQLException {
        checkConnectionNull();
        return connection.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        checkConnectionNull();
        return connection.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        checkConnectionNull();
        connection.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        checkConnectionNull();
        return connection.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        checkConnectionNull();
        connection.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        checkConnectionNull();
        return connection.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level)
            throws SQLException {
        checkConnectionNull();
        connection.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        checkConnectionNull();
        return connection.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        checkConnectionNull();
        return connection.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        checkConnectionNull();
        connection.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType,
                                     int resultSetConcurrency)
            throws SQLException {
        checkConnectionNull();
        return connection.createStatement(resultSetType,
                resultSetConcurrency);
    }

    @Override
    public PreparedStatement prepareStatement(String sql,
                                              int resultSetType,
                                              int resultSetConcurrency)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql, resultSetType,
                resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql,
                                         int resultSetType,
                                         int resultSetConcurrency)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareCall(sql, resultSetType,
                resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        checkConnectionNull();
        return connection.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map)
            throws SQLException {
        checkConnectionNull();
        connection.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        checkConnectionNull();
        connection.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        checkConnectionNull();
        return connection.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        checkConnectionNull();
        return connection.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        checkConnectionNull();
        return connection.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        checkConnectionNull();
        connection.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint)
            throws SQLException {
        checkConnectionNull();
        connection.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType,
                                     int resultSetConcurrency,
                                     int resultSetHoldability)
            throws SQLException {
        checkConnectionNull();
        return connection.createStatement(resultSetType,
                resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql,
                                              int resultSetType,
                                              int resultSetConcurrency,
                                              int resultSetHoldability)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql, resultSetType,
                resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql,
                                         int resultSetType,
                                         int resultSetConcurrency,
                                         int resultSetHoldability)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareCall(sql, resultSetType,
                resultSetConcurrency, resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql,
                                              int autoGeneratedKeys)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql, autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql,
                                              int[] columnIndexes)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql, columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql,
                                              String[] columnNames)
            throws SQLException {
        checkConnectionNull();
        return connection.prepareStatement(sql, columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        checkConnectionNull();
        return connection.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        checkConnectionNull();
        return connection.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        checkConnectionNull();
        return connection.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        checkConnectionNull();
        return connection.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        checkConnectionNull();
        return connection.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value)
            throws SQLClientInfoException {
        try {
            connection.setClientInfo(name, value);
        } catch (NullPointerException exception) {
            throw new SQLClientInfoException();
        }
    }

    public void setClientInfo(Properties properties)
            throws SQLClientInfoException {
        try {
            connection.setClientInfo(properties);
        } catch (NullPointerException exception) {
            throw new SQLClientInfoException();
        }
    }

    public String getClientInfo(String name) throws SQLException {
        checkConnectionNull();
        return connection.getClientInfo(name);
    }

    public Properties getClientInfo() throws SQLException {
        checkConnectionNull();
        return connection.getClientInfo();
    }

    public Array createArrayOf(String typeName, Object[] elements)
            throws SQLException {
        checkConnectionNull();
        return connection.createArrayOf(typeName, elements);
    }

    public Struct createStruct(String typeName, Object[] attributes)
            throws SQLException {
        checkConnectionNull();
        return connection.createStruct(typeName, attributes);
    }

    public void setSchema(String schema) throws SQLException {
        checkConnectionNull();
        connection.setSchema(schema);
    }

    public String getSchema() throws SQLException {
        checkConnectionNull();
        return connection.getSchema();
    }

    public void abort(Executor executor) throws SQLException {
        checkConnectionNull();
        connection.abort(executor);
    }

    public void setNetworkTimeout(Executor executor,
                                  int milliseconds)
            throws SQLException {
        checkConnectionNull();
        connection.setNetworkTimeout(executor, milliseconds);
    }

    public int getNetworkTimeout() throws SQLException {
        checkConnectionNull();
        return connection.getNetworkTimeout();
    }

    public void beginRequest() throws SQLException {
        checkConnectionNull();
        connection.beginRequest();
    }

    public void endRequest() throws SQLException {
        checkConnectionNull();
        connection.endRequest();
    }

    public boolean setShardingKeyIfValid(ShardingKey shardingKey,
                                         ShardingKey superShardingKey,
                                         int timeout)
            throws SQLException {
        checkConnectionNull();
        return connection.setShardingKeyIfValid(shardingKey,
                superShardingKey, timeout);
    }

    public boolean setShardingKeyIfValid(ShardingKey shardingKey,
                                         int timeout)
            throws SQLException {
        checkConnectionNull();
        return connection.setShardingKeyIfValid(shardingKey, timeout);
    }

    public void setShardingKey(ShardingKey shardingKey,
                               ShardingKey superShardingKey)
            throws SQLException {
        checkConnectionNull();
        connection.setShardingKey(shardingKey, superShardingKey);
    }

    public void setShardingKey(ShardingKey shardingKey)
            throws SQLException {
        checkConnectionNull();
        connection.setShardingKey(shardingKey);
    }

    @Override
    public int compareTo(ConnectionWrapper connectionWrapper) {
        return Integer.compare(connection.hashCode(),
                connectionWrapper.getConnection().hashCode());
    }

    private void checkConnectionNull() throws SQLException {
        if (connection == null) {
            throw new SQLException("Connection is null.");
        }
    }
}
