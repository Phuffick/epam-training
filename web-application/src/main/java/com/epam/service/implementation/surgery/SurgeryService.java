package com.epam.service.implementation.surgery;

import com.epam.model.medicalparts.Surgery;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface SurgeryService {

    /**
     * Finds surgery by id
     * @param id of surgery to find
     * @return surgery
     * @throws ServiceException
     */
    Surgery findById(Long id) throws ServiceException;

    /**
     * Finds surgeries by medical card id
     * @param medicalCardId of surgeries to find
     * @return surgeries
     * @throws ServiceException
     */
    List<Surgery> findByMedicalCardId(Long medicalCardId)
            throws ServiceException;

    /**
     * Finds all surgeries
     * @return list of surgeries
     * @throws ServiceException
     */
    List<Surgery> findAll() throws ServiceException;

    /**
     * Saves surgery's data
     * @param surgery to save
     */
    void save(Surgery surgery) throws ServiceException;

    /**
     * Deletes surgery
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
