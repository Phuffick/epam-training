package com.epam.dao.implementation.user;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends MySqlBaseDao implements UserDao {

    private static final String SQL_SELECT_ALL_USERS
            = "SELECT * FROM `user`";

    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT * FROM `user` WHERE `id`=?";

    private static final String SQL_SELECT_USER_BY_LOGIN
            = "SELECT * FROM `user` WHERE `login`=?";

    private static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD
            = "SELECT `id`, `role` FROM `user` " +
            "WHERE `login` = ? AND `password` = ?";

    private static final String SQL_SELECT_USER_BY_NAME
            = "SELECT * FROM `user` WHERE `name`=?";

    private static final String SQL_SELECT_USER_BY_ROLE
            = "SELECT * FROM `user` WHERE `role`=?";

    private static final String SQL_INSERT_USER
            = "INSERT INTO `user` (`login`, `password`, `role`) " +
            "VALUES (?, ?, ?)";

    private static final String SQL_DELETE_USER_BY_ID
            = "DELETE FROM `user` WHERE `id` = ?";

    private static final String SQL_UPDATE_USER
            = "UPDATE `user` SET `login` = ?, `password` = ?, " +
            "`role` = ?  WHERE `id` = ?";

    @Override
    public List<User> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_USERS);
            resultSet = statement.executeQuery();
            return getUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public User readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<User> userList = getUsers(resultSet);
            return userList.size() != 0 ? userList.get(0) : null;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_DELETE_USER_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(User user) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_USER,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public void update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().ordinal());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public User readUserByLogin(String patternLogin)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_USER_BY_LOGIN);
            statement.setString(1, patternLogin);
            resultSet = statement.executeQuery();
            List<User> users = getUsers(resultSet);
            return users.size() != 0
                    ? getUsers(resultSet).get(0) : null;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<User> readUserByName(String patternName)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_USER_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<User> readUserByRole(Role patternRole)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_USER_BY_ROLE);
            statement.setInt(1, patternRole.ordinal());
            resultSet = statement.executeQuery();
            return getUsers(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.values()[resultSet.getInt("role")]);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    private User getUser(ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(Role.values()[resultSet.getInt("role")]);
        return user;
    }

    private List<User> getUsers(ResultSet resultSet)
            throws SQLException {
        List<User> medications = new ArrayList<>();
        while (resultSet.next()) {
            medications.add(getUser(resultSet));
        }
        return medications;
    }
}
