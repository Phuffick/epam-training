package com.epam.service.implementation.user;

import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    /**
     * Finds user by id
     * @param id of user to find
     * @return user
     * @throws ServiceException
     */
    User findById(Long id) throws ServiceException;

    /**
     * Finds all users
     * @return list of users
     * @throws ServiceException
     */
    List<User> findAll() throws ServiceException;

    /**
     * Finds users by role
     * @return list of users
     * @throws ServiceException
     */
    List<User> findByRole(Role role) throws ServiceException;

    /**
     * Saves user's data
     * @param user of user user to save
     */
    void save(User user) throws ServiceException;

    /**
     *
     * @param userId of user to change
     * @param oldPassword password to change
     * @param newPassword password to set up
     * @throws ServiceException
     */
    void changePassword(Long userId, String oldPassword,
                        String newPassword) throws ServiceException;

    /**
     * Deletes user
     * @param id of user to delete
     */
    void delete(Long id) throws ServiceException;

    /**
     * Find by login and password
     * @param login to find
     * @param password to find
     * @return user
     */
    User findByLoginAndPassword(String login, String password) throws ServiceException;
}
