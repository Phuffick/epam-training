package com.epam.service.exception;

/**
 * TransactionException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class TransactionException extends ServiceException {

    /**
     * Constructor with cause
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
