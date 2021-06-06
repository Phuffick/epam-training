package com.epam.dao.implementation.user;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;

import java.util.List;

public interface UserDao extends BaseDao<Long, User> {

    /**
     * Read user by login from db
     * @param patternLogin login
     * @return user
     */
    User readUserByLogin(String patternLogin)
            throws DaoException;

    /**
     * Read user by name from db
     * @param patternName name
     * @return users
     */
    public List<User> readUserByName(String patternName)
            throws DaoException;

    /**
     * Read user by role from db
     * @param patternRole role
     * @return user
     */
    List<User> readUserByRole(Role patternRole) throws DaoException;

    /**
     * Check validation
     * @param login login to check
     * @param password password to check
     * @return user
     */
    User readByLoginAndPassword(String login, String password)
            throws DaoException;
}
