package com.epam.dao.implementation.patient;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPatientDao extends MySqlBaseDao
        implements PatientDao {

    private static final String SQL_SELECT_ALL_PATIENTS
            = "SELECT * FROM `patient`";

    private static final String SQL_SELECT_PATIENT_BY_ID
            = "SELECT * FROM `patient` WHERE `id`=?";

    private static final String SQL_SELECT_PATIENT_BY_NAME
            = "SELECT * FROM `patient` WHERE `name`=?";

    private static final String SQL_INSERT_PATIENT
            = "INSERT INTO `patient` (`name`) VALUES (?)";

    private static final String SQL_DELETE_PATIENT_BY_ID
            = "DELETE FROM `patient` WHERE `id` = ?";

    private static final String SQL_UPDATE_PATIENT
            = "UPDATE `patient` SET `name` = ? WHERE `id` = ?";

    @Override
    public List<Patient> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_PATIENTS);
            resultSet = statement.executeQuery();
            return getPatients(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Patient readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PATIENT_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Patient> patientList = getPatients(resultSet);
            return patientList.size() != 0
                    ? patientList.get(0) : null;
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
                    SQL_DELETE_PATIENT_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Patient patient) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_PATIENT,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, patient.getName());
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
    public void update(Patient patient) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_PATIENT);
            statement.setString(1, patient.getName());
            statement.setLong(2, patient.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Patient> readPatientByName(
            String patternName) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PATIENT_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getPatients(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    private Patient getPatient(ResultSet resultSet)
            throws SQLException {
        Patient patient = new Patient();
        patient.setId(resultSet.getLong("id"));
        patient.setName(resultSet.getString("name"));
        return patient;
    }

    private List<Patient> getPatients(ResultSet resultSet)
            throws SQLException {
        List<Patient> patients = new ArrayList<>();
        while (resultSet.next()) {
            patients.add(getPatient(resultSet));
        }
        return patients;
    }
}
