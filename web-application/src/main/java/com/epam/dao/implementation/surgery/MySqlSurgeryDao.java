package com.epam.dao.implementation.surgery;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Surgery;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MySqlSurgeryDao extends MySqlBaseDao
        implements SurgeryDao {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy.MM.dd");

    private static final String SQL_SELECT_ALL_SURGERIES
            = "SELECT * FROM `surgery`";

    private static final String SQL_SELECT_SURGERY_BY_ID
            = "SELECT * FROM `surgery` WHERE `id`=?";

    private static final String SQL_SELECT_SURGERY_BY_NAME
            = "SELECT * FROM `surgery` WHERE `name`=?";

    private static final String SQL_SELECT_SURGERY_BY_DATE
            = "SELECT * FROM `surgery` WHERE `planing_date`=?";

    private static final String SQL_SELECT_SURGERY_BY_STATUS
            = "SELECT * FROM `surgery` WHERE `is_done`=?";

    private static final String SQL_SELECT_SURGERY_BY_MEDICAL_CARD_ID
            = "SELECT * FROM `surgery` WHERE `medical_card_id`=?";

    private static final String SQL_SELECT_SURGERY_BY_DOCTOR_ID
            = "SELECT * FROM `surgery` WHERE `doctor_id`=?";

    private static final String SQL_INSERT_SURGERY
            = "INSERT INTO `surgery` (`name`, `planing_date`, " +
            "`is_done`, `medical_card_id`, `doctor_id`) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_SURGERY_BY_ID
            = "DELETE FROM `surgery` WHERE `id` = ?";

    private static final String SQL_UPDATE_SURGERY
            = "UPDATE `surgery` SET `name` = ?, `planing_date` = ?, " +
            "`is_done` = ?, `medical_card_id` = ?, `doctor_id` = ? " +
            "WHERE `id` = ?";

    private static final String SQL_CHECK_DATE
            = "SELECT COUNT(*) AS `count` FROM `medical_card` " +
            "WHERE `medical_card`.admission_date <= ? " +
            "AND `medical_card`.id = ?";

    @Override
    public List<Surgery> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_SURGERIES);
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Surgery readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Surgery> surgeryList = getSurgeries(resultSet);
            return surgeryList.size() != 0
                    ? surgeryList.get(0) : null;
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
                    SQL_DELETE_SURGERY_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Surgery surgery) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_SURGERY,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, surgery.getName());
            java.util.Date utilDate
                    = surgery.getPlaningDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(2, sqlDate);
            statement.setBoolean(3, surgery.getDone());
            statement.setLong(4, surgery.getMedicalCardId());
            statement.setLong(5, surgery.getDoctorId());
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
    public void update(Surgery surgery) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_SURGERY);
            statement.setString(1, surgery.getName());
            java.util.Date utilDate
                    = surgery.getPlaningDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(2, sqlDate);
            statement.setBoolean(3, surgery.getDone());
            statement.setLong(4, surgery.getMedicalCardId());
            statement.setLong(5, surgery.getDoctorId());
            statement.setLong(6, surgery.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Surgery> readSurgeryByName(String patternName)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Surgery> readSurgeryByDischargePlanningDate(
            Calendar patternDate) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_DATE);
            statement.setString(1, DATE_FORMAT.format(patternDate));
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Surgery> readSurgeryByStatus(Boolean patternStatus)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_STATUS);
            statement.setBoolean(1, patternStatus);
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Surgery> readSurgeryByMedicalCardId(
            Long patternMedicalCardId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_MEDICAL_CARD_ID);
            statement.setLong(1, patternMedicalCardId);
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Surgery> readSurgeryByDoctorId(Long patternDoctorId)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_SURGERY_BY_DOCTOR_ID);
            statement.setLong(1, patternDoctorId);
            resultSet = statement.executeQuery();
            return getSurgeries(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean checkIfDateAfterAdmissionDate(Surgery surgery)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DATE);
            java.util.Date utilAdmissionDate
                    = surgery.getPlaningDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, surgery.getMedicalCardId());
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

    private Surgery getSurgery(ResultSet resultSet)
            throws SQLException {
        Surgery surgery = new Surgery();
        surgery.setId(resultSet.getLong("id"));
        surgery.setName(resultSet.getString("name"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resultSet.getDate("planing_date"));
        surgery.setPlaningDate((GregorianCalendar) calendar);
        surgery.setDone(resultSet.getBoolean("is_done"));
        surgery.setDoctorId(
                resultSet.getLong("doctor_id"));
        surgery.setMedicalCardId(
                resultSet.getLong("medical_card_id"));
        return surgery;
    }

    private List<Surgery> getSurgeries(ResultSet resultSet)
            throws SQLException {
        List<Surgery> medications = new ArrayList<>();
        while (resultSet.next()) {
            medications.add(getSurgery(resultSet));
        }
        return medications;
    }
}
