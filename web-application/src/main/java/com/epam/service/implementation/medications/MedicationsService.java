package com.epam.service.implementation.medications;

import com.epam.model.medicalparts.Medications;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface MedicationsService {

    /**
     * Finds medications by id
     * @param id of medication to find
     * @return patient
     * @throws ServiceException
     */
    Medications findById(Long id) throws ServiceException;

    /**
     * Finds medications by medical card id
     * @param medicalCard of medications to find
     * @return medications
     * @throws ServiceException
     */
    List<Medications> findByMedicalCardId(Long medicalCard)
            throws ServiceException;

    /**
     * Finds all patient
     * @return list of medications
     * @throws ServiceException
     */
    List<Medications> findAll() throws ServiceException;

    /**
     * Saves medication's data
     * @param patient to save
     */
    void save(Medications patient) throws ServiceException;

    /**
     * Deletes medication
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
