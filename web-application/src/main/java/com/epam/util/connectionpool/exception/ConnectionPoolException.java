package com.epam.util.connectionpool.exception;

/**
 * ConnectionPoolException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ConnectionPoolException extends Exception {

    /**
     * Default constructor
     */
    public ConnectionPoolException() {}

    /**
     * Constructor with cause
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}