package com.epam.service.implementation.patient;

import com.epam.model.actors.Patient;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface PatientService {

    /**
     * Finds patient by id
     * @param id of patient to find
     * @return patient
     * @throws ServiceException
     */
    Patient findById(Long id) throws ServiceException;

    /**
     * Finds all patient
     * @return list of patients
     * @throws ServiceException
     */
    List<Patient> findAll() throws ServiceException;

    /**
     * Saves patient's data
     * @param patient to save
     */
    void save(Patient patient) throws ServiceException;

    /**
     * Deletes patient
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
