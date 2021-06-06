package com.epam.service.implementation.user;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.user.UserDao;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.implementation.BaseService;
import com.epam.service.exception.*;
import com.epam.service.implementation.user.exception.UserDoesNotExistsException;
import com.epam.service.implementation.user.exception.UserLoginIsNotUniqueException;
import com.epam.service.implementation.user.exception.UserNewPasswordIsIncorrectException;
import com.epam.service.implementation.user.exception.UserPasswordIsIncorrectException;

import java.util.List;

public class UserServiceImpl extends BaseService
        implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByRole(Role role) throws ServiceException {
        try {
            return userDao.readUserByRole(role);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if(user.getId() != null) {
                User storedUser
                        = userDao.readEntityById(user.getId());
                if(storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if(storedUser.getLogin().equals(user.getLogin())
                            || userDao.readUserByLogin(
                                    user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginIsNotUniqueException(
                                user.getLogin());
                    }
                } else {
                    throw new UserDoesNotExistsException(
                            user.getId());
                }
            } else {
                user.setPassword("mustbechanged");
                if(userDao.readUserByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginIsNotUniqueException(
                            user.getLogin());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw e;
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword,
                               String newPassword)
            throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.readEntityById(userId);
            if(user != null) {
                if(user.getPassword().equals(oldPassword)) {
                    if(newPassword == null) {
                        throw new UserNewPasswordIsIncorrectException();
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIsIncorrectException(
                            user.getId());
                }
            } else {
                throw new UserDoesNotExistsException(userId);
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password)
            throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
