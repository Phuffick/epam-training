package com.epam.dao.implementation.doctor;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDoctorDao extends MySqlBaseDao
        implements DoctorDao {

    private static final String SQL_SELECT_ALL_DOCTORS
            = "SELECT * FROM `doctor`";

    private static final String SQL_SELECT_DOCTOR_BY_ID
            = "SELECT * FROM `doctor` WHERE `id`=?";

    private static final String SQL_SELECT_DOCTOR_BY_NAME
            = "SELECT * FROM `doctor` WHERE `name`=?";

    private static final String SQL_INSERT_DOCTOR
            = "INSERT INTO `doctor` (`id`, `name`) VALUES (?, ?)";

    private static final String SQL_DELETE_DOCTOR_BY_ID
            = "DELETE FROM `doctor` WHERE `id` = ?";

    private static final String SQL_UPDATE_DOCTOR
            = "UPDATE `doctor` SET `name` = ? WHERE `id` = ?";

    private static final String SQL_CHECK_DOCTOR_DIAGNOSES
            = "SELECT COUNT(*) AS `count` " +
            "FROM (SELECT `id` FROM `diagnosis` " +
            "WHERE `doctor_id` = ? LIMIT 1) AS `t`";

    private static final String SQL_CHECK_DOCTOR_SURGERIES
            = "SELECT COUNT(*) AS `count` " +
            "FROM (SELECT `id` FROM `surgery` " +
            "WHERE `doctor_id` = ? LIMIT 1) AS `t`";

    @Override
    public List<Doctor> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_DOCTORS);
            resultSet = statement.executeQuery();
            return getDoctors(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Doctor readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_DOCTOR_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Doctor> doctorsList = getDoctors(resultSet);
            return doctorsList.size() != 0
                    ? doctorsList.get(0) : null;
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
                    SQL_DELETE_DOCTOR_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Doctor doctor) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_DOCTOR);
            statement.setLong(1, doctor.getId());
            statement.setString(2, doctor.getName());
            statement.executeUpdate();
            return doctor.getId();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public void update(Doctor doctor) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_DOCTOR);
            statement.setString(1, doctor.getName());
            statement.setLong(2, doctor.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Doctor> readDoctorByName(String patternName) 
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_DOCTOR_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getDoctors(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean hasDoctorDiagnoses(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DOCTOR_DIAGNOSES);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getBoolean("count");
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean hasDoctorSurgeries(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DOCTOR_SURGERIES);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                result = resultSet.getBoolean("count");
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    private Doctor getDoctor(ResultSet resultSet)
            throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(resultSet.getLong("id"));
        doctor.setName(resultSet.getString("name"));
        return  doctor;
    }

    private List<Doctor> getDoctors(ResultSet resultSet)
            throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        while (resultSet.next()) {
            doctors.add(getDoctor(resultSet));
        }
        return doctors;
    }
}
