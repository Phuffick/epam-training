package com.epam.dao.implementation.diagnosis;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.Diagnosis;

import java.util.GregorianCalendar;
import java.util.List;

public interface DiagnosisDao extends BaseDao<Long, Diagnosis> {

    /**
     * Has one diagnosis at least in database
     * @return has one at least
     */
    public boolean existsAtLeastOneByMedicalCard(Long medicalCardId)
            throws DaoException;

    /**
     * Read diagnosis by description from db
     * @param patternDescription description
     * @return diagnosis list
     */
    List<Diagnosis> readDiagnosisByDescription(
            String patternDescription) throws DaoException;

    /**
     * Read diagnosis by date from db
     * @param patternDate date
     * @return diagnosis list
     */
    List<Diagnosis> readDiagnosisByDate(GregorianCalendar patternDate)
            throws DaoException;

    /**
     * Read diagnosis by doctor id from db
     * @param patternDoctorId doctor id
     * @return diagnosis list
     */
    List<Diagnosis> readDiagnosisByDoctorId(Long patternDoctorId)
            throws DaoException;

    /**
     * Read diagnosis by medical card id id from db
     * @param patternMedicalCardId medical card id
     * @return diagnosis list
     */
    List<Diagnosis> readDiagnosisByMedicalCardId(
            Long patternMedicalCardId) throws DaoException;

    /**
     * Checks if admission date before curr date
     * @return true if admission date before curr date
     */
    boolean checkIfDateAfterAdmissionDate(Diagnosis diagnosis)
            throws DaoException;
}
