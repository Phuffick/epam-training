package com.epam.dao.implementation.procedure;

import com.epam.dao.implementation.MySqlBaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Procedure;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MySqlProcedureDao extends MySqlBaseDao
        implements ProcedureDao {

    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy.MM.dd");

    private static final String SQL_SELECT_ALL_PROCEDURES
            = "SELECT * FROM `procedure`";

    private static final String SQL_SELECT_PROCEDURE_BY_ID
            = "SELECT * FROM `procedure` WHERE `id`=?";

    private static final String SQL_SELECT_PROCEDURE_BY_NAME
            = "SELECT * FROM `procedure` WHERE `name`=?";

    private static final String SQL_SELECT_PROCEDURE_BY_REQ_COUNT
            = "SELECT * FROM `procedure` WHERE `required_count`=?";

    private static final String SQL_SELECT_PROCEDURE_BY_COUNT_CONSUMED
            = "SELECT * FROM `procedure` WHERE `amount_per_day`=?";

    private static final String SQL_SELECT_PROCEDURE_BY_DATE
            = "SELECT * FROM `procedure` WHERE `date`=?";

    private static final String SQL_SELECT_PROCEDURE_BY_MEDICAL_CARD_ID
            = "SELECT * FROM `procedure` WHERE `medical_card_id`=?";

    private static final String SQL_INSERT_PROCEDURE
            = "INSERT INTO `procedure` (`name`, `required_count`, " +
            "`required_count_consumed`, `date`, " +
            "`medical_card_id`) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_PROCEDURE_BY_ID
            = "DELETE FROM `procedure` WHERE `id` = ?";

    private static final String SQL_UPDATE_PROCEDURE
            = "UPDATE `procedure` SET `name` = ?, " +
            "`required_count` = ?, `required_count_consumed` = ?, " +
            "`date` = ?, `medical_card_id` = ?  WHERE `id` = ?";

    private static final String SQL_CHECK_DATE
            = "SELECT COUNT(*) AS `count` FROM `medical_card` " +
            "WHERE `medical_card`.admission_date <= ? " +
            "AND `medical_card`.id = ?";

    private static final String SQL_CHECK_REQUIRED_COUNT
            = "SELECT COUNT(*) AS `count` FROM `procedure` " +
            "WHERE (`procedure`.required_count <= ? " +
            "AND `procedure`.required_count_consumed <= ?)" +
            "AND `procedure`.id = ?";

    private static final String SQL_CHECK_REQUIRED_COUNT_CONSUMED
            = "SELECT COUNT(*) AS `count` FROM `procedure` " +
            "WHERE (`procedure`.required_count >= ? " +
            "AND `procedure`.required_count_consumed <= ?) " +
            "AND `procedure`.id = ?";

    @Override
    public List<Procedure> readAll() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_SELECT_ALL_PROCEDURES);
            resultSet = statement.executeQuery();
            return getProcedures(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Procedure readEntityById(Long id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PROCEDURE_BY_ID);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Procedure> procedureList = getProcedures(resultSet);
            return procedureList.size() != 0
                    ? procedureList.get(0) : null;
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
                    SQL_DELETE_PROCEDURE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public Long create(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection()
                    .prepareStatement(SQL_INSERT_PROCEDURE,
                            statement.RETURN_GENERATED_KEYS);
            statement.setString(1, procedure.getName());
            statement.setInt(2, procedure.getRequiredCount());
            statement.setInt(3, procedure.getRequiredCountConsumed());
            java.util.Date utilDate = procedure.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(4, sqlDate);
            statement.setLong(5, procedure.getMedicalCardId());
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
    public void update(Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_UPDATE_PROCEDURE);
            statement.setString(1, procedure.getName());
            statement.setInt(2, procedure.getRequiredCount());
            statement.setInt(3, procedure.getRequiredCountConsumed());
            java.util.Date utilDate = procedure.getDate().getTime();
            java.sql.Date sqlDate
                    = new java.sql.Date(utilDate.getTime());
            statement.setDate(4, sqlDate);
            statement.setLong(5, procedure.getMedicalCardId());
            statement.setLong(6, procedure.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Procedure> readProcedureByName(String patternName)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PROCEDURE_BY_NAME);
            statement.setString(1, patternName);
            resultSet = statement.executeQuery();
            return getProcedures(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Procedure> readProcedureByRequiredCount(
            Integer patternRequiredCount) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PROCEDURE_BY_REQ_COUNT);
            statement.setInt(1, patternRequiredCount);
            resultSet = statement.executeQuery();
            return getProcedures(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Procedure> readProcedureByRequiredCountConsumed(
            Integer patternRequiredCountConsumed)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PROCEDURE_BY_COUNT_CONSUMED);
            statement.setInt(1, patternRequiredCountConsumed);
            resultSet = statement.executeQuery();
            return getProcedures(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Procedure> readProcedureByDate(
            GregorianCalendar patternDate) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SELECT_PROCEDURE_BY_DATE);
            statement.setString(1, DATE_FORMAT.format(patternDate));
            resultSet = statement.executeQuery();
            return getProcedures(resultSet);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try{ statement.close(); } catch(Exception ignored) {}
            try{ resultSet.close(); } catch(Exception ignored) {}
        }
    }

    @Override
    public List<Procedure> readProcedureByMedicalCardId(
            Long patternMedicalCardId) throws DaoException {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = getConnection().prepareStatement(
                        SQL_SELECT_PROCEDURE_BY_MEDICAL_CARD_ID);
                statement.setLong(1, patternMedicalCardId);
                resultSet = statement.executeQuery();
                return getProcedures(resultSet);
            } catch (SQLException e) {
                throw new DaoException();
            } finally {
                try{ statement.close(); } catch(Exception ignored) {}
                try{ resultSet.close(); } catch(Exception ignored) {}
            }
    }

    @Override
    public boolean checkIfDateAfterAdmissionDate(Procedure procedure)
            throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_DATE);
            java.util.Date utilAdmissionDate
                    = procedure.getDate().getTime();
            java.sql.Date sqlAdmissionDate
                    = new java.sql.Date(utilAdmissionDate.getTime());
            statement.setDate(1, sqlAdmissionDate);
            statement.setLong(2, procedure.getMedicalCardId());
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
    public boolean checkIfRequiredCountIsGraterThanDoneCount(
            Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_REQUIRED_COUNT);
            statement.setInt(1, procedure.getRequiredCount());
            statement.setInt(2, procedure.getRequiredCount());
            statement.setLong(3, procedure.getId());
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
    public boolean checkIfDoneCountIsGraterThanRequiredCount(
            Procedure procedure) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = true;
        try {
            statement = getConnection().prepareStatement(
                    SQL_CHECK_REQUIRED_COUNT_CONSUMED);
            statement.setInt(1, procedure.getRequiredCountConsumed());
            statement.setInt(2, procedure.getRequiredCountConsumed());
            statement.setLong(3, procedure.getId());
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

    private Procedure getProcedure(ResultSet resultSet) throws SQLException {
        Procedure procedure = new Procedure();
        procedure.setId(resultSet.getLong("id"));
        procedure.setName(resultSet.getString("name"));
        procedure.setRequiredCount(
                resultSet.getInt("required_count"));
        procedure.setRequiredCountConsumed(
                resultSet.getInt("required_count_consumed"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resultSet.getDate("date"));
        procedure.setDate((GregorianCalendar) calendar);
        procedure.setMedicalCardId(
                resultSet.getLong("medical_card_id"));
        return procedure;
    }

    private List<Procedure> getProcedures(ResultSet resultSet)
            throws SQLException {
        List<Procedure> procedures = new ArrayList<>();
        while (resultSet.next()) {
            procedures.add(getProcedure(resultSet));
        }
        return procedures;
    }
}
