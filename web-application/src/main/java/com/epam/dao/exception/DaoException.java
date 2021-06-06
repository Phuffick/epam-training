package com.epam.dao.exception;

/**
 * DaoException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DaoException extends Exception {

    /**
     * Default constructor
     */
    public DaoException() {}

    /**
     * Constructor with message and cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructor with cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
