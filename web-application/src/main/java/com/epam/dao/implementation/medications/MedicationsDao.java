package com.epam.dao.implementation.medications;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Medications;
import com.epam.model.medicalparts.Procedure;

import java.util.GregorianCalendar;
import java.util.List;

public interface MedicationsDao extends BaseDao<Long, Medications> {

    /**
     * Read medications by name from db
     * @param patternName name
     * @return medications list
     */
    List<Medications> readMedicationsByName(String patternName)
            throws DaoException;

    /**
     * Read medications by status from db
     * @param patternStatus status
     * @return medications list
     */
    List<Medications> readMedicationsByStatus(Boolean patternStatus)
            throws DaoException;

    /**
     * Read medications by req count from db
     * @param patternRequiredCount req count
     * @return medications list
     */
    List<Medications> readMedicationsByRequiredCount(
            Integer patternRequiredCount) throws DaoException;

    /**
     * Read medications by amount per day from db
     * @param patternRequiredAmountPerDay amount per day
     * @return medications list
     */
    List<Medications> readMedicationsByRequiredAmountPerDay(
            Integer patternRequiredAmountPerDay) throws DaoException;

    /**
     * Read medications by date from db
     * @param patternDate date
     * @return medications list
     */
    List<Medications> readMedicationsByDate(
            GregorianCalendar patternDate) throws DaoException;

    /**
     * Read medications by patient id from db
     * @param patternPatientId patient id
     * @return medications list
     */
    List<Medications> readMedicationsByMedicalCardId(
            Long patternPatientId) throws DaoException;

    /**
     * Checks if admission date before curr date
     * @return true if admission date before curr date
     */
    boolean checkIfDateAfterAdmissionDate(Medications medications)
            throws DaoException;

    /**
     * Checks if required count is grater than amount per day count
     * @return true if required count is grater than amount per day count
     */
    boolean checkIfRequiredCountIsGraterThanAmountPerDayCount(
            Medications medications) throws DaoException;

    /**
     * Checks if amount per day count is less than required count
     * @return true if amount per day count is less than required count
     */
    boolean checkIfAmountPerDayCountIsLessThanRequiredCount(
            Medications medications) throws DaoException;
}
