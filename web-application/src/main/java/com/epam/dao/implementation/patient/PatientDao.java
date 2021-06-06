package com.epam.dao.implementation.patient;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.Patient;

import java.util.List;

public interface PatientDao extends BaseDao<Long, Patient> {

    /**
     * Read patient by name from db
     * @param patternName name
     * @return patient list
     */
    List<Patient> readPatientByName(String patternName)
            throws DaoException;
}
