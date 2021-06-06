package com.epam.dao.implementation.diagnosis;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Diagnosis;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MySqlDiagnosisDao extends MySqlBaseDao
        implements DiagnosisDao {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy.MM.dd");

    private static final String SQL_SELECT_ALL_DIAGNOSES
            = "SELECT * FROM `diagnosis`";

    private static final String SQL_HAS_ONE_AT_LEAST_BY_MEDICAL_CARD
            = "SELECT * FROM `diagnosis` WHERE medical_card_id=? " +
            "HAVING COUNT(*) > 0";

    private static final String SQL_SELECT_DIAGNOSIS_BY_ID
            = "SELECT * FROM `diagnosis` WHERE `id`=?";

    private static final String SQL_SELECT_DIAGNOSIS_BY_DESCRIPTION
            = "SELECT * FROM `diagnosis` WHERE `description`=?";

    private static final String SQL_SELECT_DIAGNOSIS_BY_DATE
            = "SELECT * FROM `diagnosis` WHERE `date`=?";

    private static final String SQL_SELECT_DIAGNOSIS_BY_DOCTOR_ID
            = "SELECT * FROM `diagnosis` WHERE `doctor_id`=?";

    private static final String SQL_SELECT_DIAGNOSIS_BY_MEDICAL_CARD_ID
            = "SELECT * FROM `diagnosis` WHERE `medical_card_id`=?";

    private static final String SQL_INSERT_DIAGNOSIS
            = "INSERT INTO `diagnosis` (`description`, `date`, " +
            "`doctor_id`, `medical_card_id`) VALUES (?, ?, ?, ?)";

    private static final String SQL_DELETE_DIAGNOSIS_BY_ID
            = "DELETE FROM `diagnosis` WHERE `id` = ?";

    private static final String SQL_UPDATE_DIAGNOSIS
            = "UPDATE `diagnosis` SET `description` = ?, `date` = ?, " +
            "`doctor_id` = ?, `medical_card_id` = ? WHERE `id` = ?";

    private static final String SQL_CHECK_DATE
            = "SELECT COUNT(*) AS `count` FROM `medical_card` " +
            "WHERE `medical_card`.admission_date <= ? " +
            "AND `medical_card`.id = ?";

    @Override
    public List<Diagnosis> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_DIAGNOSES);
            resultSet = statement.executeQuery();
            return getDiagnoses(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean existsAtLeastOneByMedicalCard(Long medicalCardId)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_HAS_ONE_AT_LEAST_BY_MEDICAL_CARD);
            statement.setLong(1, medicalCardId);
            resultSet = statement.executeQuery();
            return getDiagnoses(resultSet).size() > 0;
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Diagnosis readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_DIAGNOSIS_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Diagnosis> diagnosisList = getDiagnoses(resultSet);
            return diagnosisList.size() != 0
                    ? diagnosisList.get(0) : null;
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
                    SQL_DELETE_DIAGNOSIS_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Diagnosis diagnosis) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_DIAGNOSIS,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, diagnosis.getDescription());
            java.util.Date utilDate
                    = diagnosis.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(2, sqlDate);
            statement.setLong(3, diagnosis.getDoctorId());
            statement.setLong(4, diagnosis.getMedicalCardId());
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
    public void update(Diagnosis diagnosis) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_DIAGNOSIS);
            statement.setString(1, diagnosis.getDescription());
            java.util.Date utilDate
                    = diagnosis.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(2, sqlDate);
            statement.setLong(3, diagnosis.getDoctorId());
            statement.setLong(4, diagnosis.getMedicalCardId());
            statement.setLong(5, diagnosis.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Diagnosis> readDiagnosisByDescription(
            String patternDescription) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_DIAGNOSIS_BY_DESCRIPTION);
            statement.setString(1, patternDescription);
            resultSet = statement.executeQuery();
            return getDiagnoses(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Diagnosis> readDiagnosisByDate(
            GregorianCalendar patternDate) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_DIAGNOSIS_BY_DATE);
            statement.setString(1, DATE_FORMAT.format(patternDate));
            resultSet = statement.executeQuery();
            return getDiagnoses(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Diagnosis> readDiagnosisByDoctorId(
            Long patternDoctorId) throws DaoException {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = getConnection().prepareStatement(
                        SQL_SELECT_DIAGNOSIS_BY_DOCTOR_ID);
                statement.setLong(1, patternDoctorId);
                resultSet = statement.executeQuery();
                return getDiagnoses(resultSet);
            } catch (SQLException e) {
                throw new DaoException();
            } finally {
                try{ statement.close(); } catch(Exception ignored) {}
                try{ resultSet.close(); } catch(Exception ignored) {}
            }
    }

    @Override
    public List<Diagnosis> readDiagnosisByMedicalCardId(
            Long patternMedicalCardId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_DIAGNOSIS_BY_MEDICAL_CARD_ID);
            statement.setLong(1, patternMedicalCardId);
            resultSet = statement.executeQuery();
            return getDiagnoses(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean checkIfDateAfterAdmissionDate(Diagnosis diagnosis)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DATE);
            java.util.Date utilAdmissionDate
                    = diagnosis.getDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, diagnosis.getMedicalCardId());
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

    private Diagnosis getDiagnosis(ResultSet resultSet)
            throws SQLException {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(resultSet.getLong("id"));
        diagnosis.setDescription(
                resultSet.getString("description"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resultSet.getDate("date"));
        diagnosis.setDate((GregorianCalendar) calendar);
        diagnosis.setDoctorId(
                resultSet.getLong("doctor_id"));
        diagnosis.setMedicalCardId(
                resultSet.getLong("medical_card_id"));
        return diagnosis;
    }

    private List<Diagnosis> getDiagnoses(ResultSet resultSet)
            throws SQLException {
        List<Diagnosis> diagnoses = new ArrayList<>();
        while (resultSet.next()) {
            diagnoses.add(getDiagnosis(resultSet));
        }
        return diagnoses;
    }
}
