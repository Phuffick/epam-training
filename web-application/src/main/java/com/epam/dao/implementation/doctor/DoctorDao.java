package com.epam.dao.implementation.doctor;

import com.epam.dao.BaseDao;
import com.epam.dao.exception.DaoException;
import com.epam.model.actors.Doctor;

import java.util.List;

public interface DoctorDao extends BaseDao<Long, Doctor> {

    /**
     * Read doctor by name from db
     * @param patternName name
     * @return doctor list
     */
    List<Doctor> readDoctorByName(String patternName)
            throws DaoException;

    /**
     * Checks if doctor is mentioned in diagnoses
     * @return is doctor mentioned
     * @param id doctor id
     */
    boolean hasDoctorDiagnoses(Long id) throws DaoException;

    /**
     * Checks if doctor is mentioned in surgeries
     * @return is doctor mentioned
     * @param id doctor id
     */
    boolean hasDoctorSurgeries(Long id) throws DaoException;
}
