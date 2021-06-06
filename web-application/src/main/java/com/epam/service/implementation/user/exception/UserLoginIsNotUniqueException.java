package com.epam.service.implementation.user.exception;

import com.epam.service.exception.ServiceException;

/**
 * UserLoginIsNotUniqueException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserLoginIsNotUniqueException extends ServiceException {

    /** Invalid login */
    private String login;

    /**
     * Default constructor
     * @param login of exception cause user
     */
    public UserLoginIsNotUniqueException(String login) {
        this.login = login;
    }

    /**
     * Invalid login getter
     * @return login
     */
    public String getLogin() {
        return login;
    }
}
