package com.epam.service;

import com.epam.service.exception.TransactionException;

/**
 * Transaction interface definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public interface Transaction {

    /**
     * Transaction started.
     */
    void start() throws TransactionException;

    /**
     * Transaction committed
     */
    void commit() throws TransactionException;

    /**
     * Transaction rollback.
     * If smth went wrong.
     */
    void rollback() throws TransactionException;
}
