package com.epam.service.implementation.user.exception;

import com.epam.service.exception.ServiceException;

/**
 * UserDoesNotExistsException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserDoesNotExistsException extends ServiceException {

    /** Id in db */
    private Long id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public UserDoesNotExistsException(Long id) {
        this.id = id;
    }

    /**
     * Id getter
     * @return id
     */
    public Long getId() {
        return id;
    }
}
