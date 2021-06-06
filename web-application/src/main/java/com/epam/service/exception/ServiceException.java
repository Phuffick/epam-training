package com.epam.service.exception;

/**
 * ServiceException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ServiceException extends Exception {

    /**
     * Default constructor
     */
    public ServiceException() {}

    /**
     * Constructor with message and cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructor with cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
