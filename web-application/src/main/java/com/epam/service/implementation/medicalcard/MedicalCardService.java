package com.epam.service.implementation.medicalcard;

import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface MedicalCardService {

    /**
     * Finds medical card from db where discharge date is not null
     * @return list of medical cards
     * @throws ServiceException
     */
    List<MedicalCard> findMedicalCardsWithDischargeDate()
            throws ServiceException;

    /**
     * Finds medical card from db where discharge date is null
     * @return list of medical cards
     * @throws ServiceException
     */
    List<MedicalCard> findMedicalCardsWithoutDischargeDate()
            throws ServiceException;

    /**
     * Finds medical card by id
     * @param id of medical card to find
     * @return patient
     * @throws ServiceException
     */
    MedicalCard findById(Long id) throws ServiceException;

    /**
     * Finds all medical cards
     * @return list of medical cards
     * @throws ServiceException
     */
    List<MedicalCard> findAll() throws ServiceException;

    /**
     * Saves medical card's data
     * @param medicalCard to save
     */
    void save(MedicalCard medicalCard) throws ServiceException;

    /**
     * Deletes medical card
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
