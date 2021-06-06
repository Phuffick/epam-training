package com.epam.dao.implementation.medicalcard;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.MedicalCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MySqlMedicalCardDao extends MySqlBaseDao
        implements MedicalCardDao {

    private static final String SQL_SELECT_ALL_MEDICAL_CARD
            = "SELECT * FROM `medical_card`";

    private static final String SQL_SELECT_MEDICAL_CARDS_WITH_DISCHARGE_DATE
            = "SELECT * FROM `medical_card` " +
            "WHERE `discharge_date` IS NOT NULL";

    private static final String SQL_SELECT_MEDICAL_CARDS_WITHOUT_DISCHARGE_DATE
            = "SELECT * FROM `medical_card` " +
            "WHERE `discharge_date` IS NULL";

    private static final String SQL_SELECT_MEDICAL_CARD_BY_ID
            = "SELECT * FROM `medical_card` WHERE `id`=?";

    private static final String SQL_SELECT_MEDICAL_CARD_BY_ADMISSION_DATE
            = "SELECT * FROM `medical_card` WHERE `admission_date`=?";

    private static final String SQL_SELECT_MEDICAL_CARD_BY_DISCHARGE_DATE
            = "SELECT * FROM `medical_card` WHERE `discharge_date`=?";

    private static final String SQL_SELECT_MEDICAL_CARD_BY_PATIENT_ID
            = "SELECT * FROM `medical_card` WHERE `patient_id`=?";

    private static final String SQL_INSERT_MEDICAL_CARD_WITH_TWO_DATES
            = "INSERT INTO `medical_card` (`admission_date`, " +
            "`discharge_date`, `patient_id`) VALUES (?, ?, ?)";

    private static final String SQL_INSERT_MEDICAL_CARD_WITH_ONE_DATE
            = "INSERT INTO `medical_card` (`admission_date`, " +
            "`patient_id`) VALUES (?, ?)";

    private static final String SQL_DELETE_MEDICAL_CARD_BY_ID
            = "DELETE FROM `medical_card` WHERE `id` = ?";

    private static final String SQL_UPDATE_MEDICAL_CARD
            = "UPDATE `medical_card` SET `admission_date` = ?, " +
            "`discharge_date` = ?, `patient_id` = ? WHERE `id` = ?";

    private static final String SQL_UPDATE_MEDICAL_CARD_WITHOUT_DISCHARGE_DATE
            = "UPDATE `medical_card` SET `admission_date` = ?, " +
            "`patient_id` = ? WHERE `id` = ?";

    @Override
    public List<MedicalCard> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_MEDICAL_CARD);
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<MedicalCard> readMedicalCardsWithDischargeDate()
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                            SQL_SELECT_MEDICAL_CARDS_WITH_DISCHARGE_DATE);
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<MedicalCard> readMedicalCardsWithoutDischargeDate()
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICAL_CARDS_WITHOUT_DISCHARGE_DATE);
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public MedicalCard readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICAL_CARD_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<MedicalCard> medicalCardList
                    = getMedicalCards(resultSet);
            return medicalCardList.size() != 0
                    ? medicalCardList.get(0) : null;
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
                    SQL_DELETE_MEDICAL_CARD_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long createWithoutDischargeDate(MedicalCard medicalCard)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_MEDICAL_CARD_WITH_ONE_DATE,
                            statement.RETURN_GENERATED_KEYS);
            java.util.Date utilAdmissionDate
                    = medicalCard.getAdmissionDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, medicalCard.getPatientId());
            Long id = null;
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception ignored) {
            }
            try {
                resultSet.close();
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public Long create(MedicalCard medicalCard) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_MEDICAL_CARD_WITH_TWO_DATES,
                            statement.RETURN_GENERATED_KEYS);
            java.util.Date utilAdmissionDate
                    = medicalCard.getAdmissionDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            java.util.Date utilDischargeDate
                    = medicalCard.getDischargeDate().getTime();
            java.sql.Date sqlDischargeDate
                    = new java.sql.Date(utilDischargeDate.getTime());
            statement.setDate(2, sqlDischargeDate);
            statement.setLong(3, medicalCard.getPatientId());
            Long id = null;
            statement.executeUpdate();
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
    public void updateWithoutDischargeDate(MedicalCard medicalCard)
            throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_MEDICAL_CARD_WITHOUT_DISCHARGE_DATE);
            java.util.Date utilAdmissionDate
                    = medicalCard.getAdmissionDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, medicalCard.getPatientId());
            statement.setLong(3, medicalCard.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public void update(MedicalCard medicalCard) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_MEDICAL_CARD);
            java.util.Date utilAdmissionDate
                    = medicalCard.getAdmissionDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            java.util.Date utilDischargeDate
                    = medicalCard.getDischargeDate().getTime();
            java.sql.Date sqlDischargeDate
                    = new java.sql.Date(utilDischargeDate.getTime());
            statement.setDate(2, sqlDischargeDate);
            statement.setLong(3, medicalCard.getPatientId());
            statement.setLong(4, medicalCard.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<MedicalCard> readMedicalCardByAdmissionDate(
            GregorianCalendar patternAdmissionDate)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICAL_CARD_BY_ADMISSION_DATE);
            statement.setDate(1,
                    (Date) patternAdmissionDate.getTime());
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<MedicalCard> readMedicalCardByDischargeDate(
            GregorianCalendar patternDischargeDate)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICAL_CARD_BY_DISCHARGE_DATE);
            statement.setDate(1,
                    (Date) patternDischargeDate.getTime());
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<MedicalCard> readMedicalCardByPatientId(
            Long patternPatientId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_MEDICAL_CARD_BY_PATIENT_ID);
            statement.setLong(1, patternPatientId);
            resultSet = statement.executeQuery();
            return getMedicalCards(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    private MedicalCard getMedicalCard(ResultSet resultSet)
            throws SQLException {
        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(resultSet.getLong("id"));
        Calendar admissionDate = Calendar.getInstance();
        admissionDate.setTime(
                resultSet.getDate("admission_date"));
        medicalCard.setAdmissionDate(
                (GregorianCalendar) admissionDate);
        Calendar dischargeDate = Calendar.getInstance();
        Date dischargeDateStored
                = resultSet.getDate("discharge_date");
        if (dischargeDateStored != null) {
            dischargeDate.setTime(dischargeDateStored);
        } else {
            dischargeDate = null;
        }
        medicalCard.setDischargeDate(
                (GregorianCalendar) dischargeDate);
        medicalCard.setPatientId(
                resultSet.getLong("patient_id"));
        return medicalCard;
    }

    private List<MedicalCard> getMedicalCards(ResultSet resultSet)
            throws SQLException {
        List<MedicalCard> medicalCards = new ArrayList<>();
        while (resultSet.next()) {
            medicalCards.add(getMedicalCard(resultSet));
        }
        return medicalCards;
    }
}
