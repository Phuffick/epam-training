package com.epam.service.implementation.procedure;

import com.epam.model.medicalparts.Procedure;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface ProcedureService {

    /**
     * Finds procedure by id
     * @param id of procedure to find
     * @return procedure
     * @throws ServiceException
     */
    Procedure findById(Long id) throws ServiceException;

    /**
     * Finds procedures by medical card id
     * @param medicalCardId of procedures to find
     * @return procedures
     * @throws ServiceException
     */
    List<Procedure> findByMedicalCardId(Long medicalCardId)
            throws ServiceException;

    /**
     * Finds all procedure
     * @return list of procedure
     * @throws ServiceException
     */
    List<Procedure> findAll() throws ServiceException;

    /**
     * Saves procedure's data
     * @param procedure to save
     */
    void save(Procedure procedure) throws ServiceException;

    /**
     * Deletes procedure
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
