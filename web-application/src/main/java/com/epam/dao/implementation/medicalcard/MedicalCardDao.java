package com.epam.dao.implementation.medicalcard;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.medicalparts.MedicalCard;

import java.util.GregorianCalendar;
import java.util.List;

public interface MedicalCardDao extends BaseDao<Long, MedicalCard> {

    /**
     * Create medical card where discharge date is null
     */
    Long createWithoutDischargeDate(MedicalCard medicalCard)
            throws DaoException;

    /**
     * Update medical card where discharge date is null
     */
    void updateWithoutDischargeDate(MedicalCard medicalCard)
            throws DaoException;

    /**
     * Read medical card from db where discharge date is not null
     * @return medical cards list
     */
    List<MedicalCard> readMedicalCardsWithDischargeDate()
            throws DaoException;

    /**
     * Read medical card from db where discharge date is null
     * @return medical cards list
     */
    List<MedicalCard> readMedicalCardsWithoutDischargeDate()
            throws DaoException;

    /**
     * Read medical card by admission date from db
     * @param patternAdmissionDate admission date
     * @return medical cards list
     */
    List<MedicalCard> readMedicalCardByAdmissionDate(
            GregorianCalendar patternAdmissionDate) throws DaoException;

    /**
     * Read medical card by discharge date from db
     * @param patternDischargeDate discharge date
     * @return medical cards list
     */
    List<MedicalCard> readMedicalCardByDischargeDate(
            GregorianCalendar patternDischargeDate) throws DaoException;

    /**
     * Read medical card by patient id from db
     * @param patternPatientId patient id
     * @return medical cards list
     */
    List<MedicalCard> readMedicalCardByPatientId(
            Long patternPatientId) throws DaoException;
}
