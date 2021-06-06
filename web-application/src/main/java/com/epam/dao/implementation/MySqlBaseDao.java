package com.epam.dao.implementation;

import java.sql.Connection;

/**
 * MySqlBaseDao definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MySqlBaseDao {

    /** Connection */
    private Connection connection;

    /**
     * Connection getter
     * @return connector
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connection setter
     * @param connection to set up
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
