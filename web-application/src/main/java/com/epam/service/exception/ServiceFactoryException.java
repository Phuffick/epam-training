package com.epam.service.exception;

/**
 * ServiceFactoryException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ServiceFactoryException extends Exception {

    /**
     * Default constructor
     */
    public ServiceFactoryException() {}

    /**
     * Constructor with message and cause
     */
    public ServiceFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with message
     */
    public ServiceFactoryException(String message) {
        super(message);
    }

    /**
     * Constructor with cause
     */
    public ServiceFactoryException(Throwable cause) {
        super(cause);
    }
}
