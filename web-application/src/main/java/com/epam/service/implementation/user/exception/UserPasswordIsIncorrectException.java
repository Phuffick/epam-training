package com.epam.service.implementation.user.exception;

import com.epam.service.exception.ServiceException;

/**
 * UserPasswordIsIncorrectException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserPasswordIsIncorrectException extends ServiceException {

    /** Id in db */
    private Long id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public UserPasswordIsIncorrectException(Long id) {
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
