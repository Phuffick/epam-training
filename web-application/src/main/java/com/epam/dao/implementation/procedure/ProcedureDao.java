package com.epam.dao.implementation.procedure;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Procedure;

import java.util.GregorianCalendar;
import java.util.List;

public interface ProcedureDao extends BaseDao<Long, Procedure> {

    /**
     * Read procedure by name from db
     * @param patternName name
     * @return procedure list
     */
    List<Procedure> readProcedureByName(String patternName)
            throws DaoException;

    /**
     * Read procedure by req count from db
     * @param patternRequiredCount req count
     * @return procedure list
     */
    List<Procedure> readProcedureByRequiredCount(
            Integer patternRequiredCount) throws DaoException;

    /**
     * Read procedure by req count consumed from db
     * @param patternRequiredCountConsumed req count consumed
     * @return procedure list
     */
    List<Procedure> readProcedureByRequiredCountConsumed(
            Integer patternRequiredCountConsumed) throws DaoException;

    /**
     * Read procedure by date from db
     * @param patternDate date
     * @return procedure list
     */
    List<Procedure> readProcedureByDate(
            GregorianCalendar patternDate) throws DaoException;

    /**
     * Read procedure by medical card id from db
     * @param patternMedicalCardId medical card id
     * @return procedure list
     */
    List<Procedure> readProcedureByMedicalCardId(
            Long patternMedicalCardId) throws DaoException;

    /**
     * Checks if admission date before curr date
     * @return true if admission date before curr date
     */
    boolean checkIfDateAfterAdmissionDate(Procedure surgery)
            throws DaoException;

    /**
     * Checks if required count is grater than done count
     * @return true if required count is grater than done count
     */
    boolean checkIfRequiredCountIsGraterThanDoneCount(
            Procedure procedure) throws DaoException;

    /**
     * Checks if done count is less than required count
     * @return true if done count is less than required count
     */
    boolean checkIfDoneCountIsGraterThanRequiredCount(
            Procedure procedure) throws DaoException;
}
