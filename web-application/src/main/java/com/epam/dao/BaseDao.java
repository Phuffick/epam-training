package com.epam.dao;

import com.epam.dao.exception.DaoException;
import com.epam.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * BaseDao definition class.
 * First generic param (K) - primary key in DB
 * Second generic param (T) - value in java
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public interface BaseDao <K, T extends Entity> {

    static Logger LOGGER = LogManager.getLogger(BaseDao.class);

    /**
     * Read all from db
     * @return elements list
     */
    List<T> readAll() throws DaoException;

    /**
     * Read by id from db
     * @return element
     */
    T readEntityById(K id) throws DaoException;

    /**
     * Delete in db
     * @param id key
     */
    void delete(K id) throws DaoException;

    /**
     * Create in db
     * @param t value
     * @return autogenerated key from db
     */
    Long create(T t) throws DaoException;

    /**
     * Delete in db
     * @param t value
     */
    void update(T t) throws DaoException;
}
