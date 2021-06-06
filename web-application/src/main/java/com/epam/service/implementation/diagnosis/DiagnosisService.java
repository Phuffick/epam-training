package com.epam.service.implementation.diagnosis;

import com.epam.model.medicalparts.Diagnosis;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface DiagnosisService {

    /**
     * Checks if at least one diagnosis with definite card id exists
     * @param medicalCardId of medical card to check
     * @return true - exists, false - not exists
     * @throws ServiceException
     */
    boolean checkIfExistsAtLeastOneByMedicalCard(Long medicalCardId)
            throws ServiceException;

    /**
     * Finds diagnosis by id
     * @param id of diagnosis to find
     * @return diagnosis
     * @throws ServiceException
     */
    Diagnosis findById(Long id) throws ServiceException;

    /**
     * Finds diagnosis by medical card id
     * @param medicalCard of diagnosis to find
     * @return diagnosis
     * @throws ServiceException
     */
    List<Diagnosis> findByMedicalCardId(Long medicalCard)
            throws ServiceException;

    /**
     * Finds all diagnosis
     * @return list of diagnosis
     * @throws ServiceException
     */
    List<Diagnosis> findAll() throws ServiceException;

    /**
     * Saves diagnosis's data
     * @param diagnosis to save
     */
    void save(Diagnosis diagnosis) throws ServiceException;

    /**
     * Deletes diagnosis
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
