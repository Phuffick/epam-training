package com.epam.dao.implementation.medications;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Medications;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MySqlMedicationsDao  extends MySqlBaseDao
        implements MedicationsDao {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy.MM.dd");

    private static final String SQL_SELECT_ALL_MEDICATIONS
            = "SELECT * FROM `medications`";

    private static final String SQL_SELECT_MEDICATIONS_BY_ID
            = "SELECT * FROM `medications` WHERE `id`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_NAME
            = "SELECT * FROM `medications` WHERE `name`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_STATUS
            = "SELECT * FROM `medications` WHERE `is_done`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_REQ_COUNT
            = "SELECT * FROM `medications` WHERE `required_count`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_COUNT_PER_DAY
            = "SELECT * FROM `medications` WHERE `amount_per_day`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_DATE
            = "SELECT * FROM `medications` WHERE `date`=?";

    private static final String SQL_SELECT_MEDICATIONS_BY_MEDICAL_CARD_ID
            = "SELECT * FROM `medications` WHERE `medical_card_id`=?";

    private static final String SQL_INSERT_MEDICATIONS
            = "INSERT INTO `medications` (`name`, `is_done`, " +
            "`required_count`, `amount_per_day`, `date`, " +
            "`medical_card_id`) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_MEDICATIONS_BY_ID
            = "DELETE FROM `medications` WHERE `id` = ?";

    private static final String SQL_UPDATE_MEDICATIONS
            = "UPDATE `medications` SET `name` = ?, `is_done` = ?, " +
            "`required_count` = ?, `amount_per_day` = ?, `date` = ?, " +
            "`medical_card_id` = ?  WHERE `id` = ?";

    private static final String SQL_CHECK_DATE
            = "SELECT COUNT(*) AS `count` FROM `medical_card` " +
            "WHERE `medical_card`.admission_date <= ? " +
            "AND `medical_card`.id = ?";

    private static final String SQL_CHECK_REQUIRED_COUNT
            = "SELECT COUNT(*) AS `count` FROM `medications` " +
            "WHERE (`medications`.id = ? " +
            "AND `medications`.required_count <= ?) " +
            "AND `medications`.amount_per_day <= ?";

    private static final String SQL_CHECK_REQUIRED_COUNT_CONSUMED
            = "SELECT COUNT(*) AS `count` FROM `medications` " +
            "WHERE (`medications`.id = ? " +
            "AND `medications`.required_count >= ?) " +
            "AND `medications`.amount_per_day <= ?";

    @Override
    public List<Medications> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_MEDICATIONS);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Medications readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Medications> medicationsList
                    = getMedications(resultSet);
            return medicationsList.size() != 0
                    ? medicationsList.get(0) : null;
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
                    SQL_DELETE_MEDICATIONS_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Medications medications) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_MEDICATIONS,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, medications.getName());
            statement.setBoolean(2, medications.getDone());
            statement.setInt(3, medications.getRequiredCount());
            statement.setInt(4, medications.getAmountPerDay());
            java.util.Date utilDate
                    = medications.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(5, sqlDate);
            statement.setLong(6, medications.getMedicalCardId());
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
    public void update(Medications medications) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_MEDICATIONS);
            statement.setString(1, medications.getName());
            statement.setBoolean(2, medications.getDone());
            statement.setInt(3, medications.getRequiredCount());
            statement.setInt(4, medications.getAmountPerDay());
            java.util.Date utilDate
                    = medications.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(5, sqlDate);
            statement.setLong(6, medications.getMedicalCardId());
            statement.setLong(7, medications.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByName(
            String patternName) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByStatus(
            Boolean patternStatus) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_STATUS);
            statement.setBoolean(1, patternStatus);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByRequiredCount(
            Integer patternRequiredCount) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_REQ_COUNT);
            statement.setInt(1, patternRequiredCount);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByRequiredAmountPerDay(
            Integer patternRequiredAmountPerDay) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_COUNT_PER_DAY);
            statement.setInt(1, patternRequiredAmountPerDay);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByDate(
            GregorianCalendar patternDate) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_DATE);
            statement.setString(1, DATE_FORMAT.format(patternDate));
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Medications> readMedicationsByMedicalCardId(
            Long patternPatientId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICATIONS_BY_MEDICAL_CARD_ID);
            statement.setLong(1, patternPatientId);
            resultSet = statement.executeQuery();
            return getMedications(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public boolean checkIfDateAfterAdmissionDate(
            Medications medications) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DATE);
            java.util.Date utilAdmissionDate
                    = medications.getDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, medications.getMedicalCardId());
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
    public boolean checkIfRequiredCountIsGraterThanAmountPerDayCount(
            Medications medications) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_REQUIRED_COUNT);
            statement.setLong(1, medications.getMedicalCardId());
            statement.setInt(2, medications.getRequiredCount());
            statement.setInt(3, medications.getRequiredCount());
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
    public boolean checkIfAmountPerDayCountIsLessThanRequiredCount(
            Medications medications) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_REQUIRED_COUNT_CONSUMED);
            statement.setLong(1, medications.getMedicalCardId());
            statement.setInt(2, medications.getAmountPerDay());
            statement.setInt(3, medications.getAmountPerDay());
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

    private Medications getMedication(ResultSet resultSet)
            throws SQLException {
        Medications medication = new Medications();
        medication.setId(resultSet.getLong("id"));
        medication.setName(resultSet.getString("name"));
        medication.setDone(resultSet.getBoolean("is_done"));
        medication.setRequiredCount(
                resultSet.getInt("required_count"));
        medication.setAmountPerDay(
                resultSet.getInt("amount_per_day"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resultSet.getDate("date"));
        medication.setDate((GregorianCalendar) calendar);
        medication.setMedicalCardId(
                resultSet.getLong("medical_card_id"));
        return medication;
    }

    private List<Medications> getMedications(ResultSet resultSet)
            throws SQLException {
        List<Medications> medications = new ArrayList<>();
        while (resultSet.next()) {
            medications.add(getMedication(resultSet));
        }
        return medications;
    }
}
