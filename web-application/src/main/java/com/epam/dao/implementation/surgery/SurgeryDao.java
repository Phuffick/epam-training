package com.epam.dao.implementation.surgery;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Surgery;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public interface SurgeryDao extends BaseDao<Long, Surgery> {

    /**
     * Read surgery by name from db
     * @param patternName name
     * @return surgery list
     */
    List<Surgery> readSurgeryByName(String patternName)
            throws DaoException;

    /**
     * Read surgery by date from db
     * @param patternDate date
     * @return surgery list
     */
    List<Surgery> readSurgeryByDischargePlanningDate(
            Calendar patternDate) throws DaoException;

    /**
     * Read surgery by status from db
     * @param patternStatus status
     * @return surgery list
     */
    List<Surgery> readSurgeryByStatus(Boolean patternStatus)
            throws DaoException;

    /**
     * Read surgery by medical card id from db
     * @param patternMedicalCardId medical card id
     * @return surgery list
     */
    List<Surgery> readSurgeryByMedicalCardId(Long patternMedicalCardId)
            throws DaoException;

    /**
     * Read surgery by doctor id from db
     * @param patternDoctorId doctor id
     * @return surgery list
     */
    List<Surgery> readSurgeryByDoctorId(Long patternDoctorId)
            throws DaoException;

    /**
     * Checks if admission date before curr date
     * @return true if admission date before curr date
     */
    boolean checkIfDateAfterAdmissionDate(Surgery surgery)
            throws DaoException;
}
