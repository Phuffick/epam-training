package com.epam.service.implementation.medicalcard.exception;

import com.epam.service.exception.ServiceException;

/**
 * MedicalCardDoesNotExistsException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicalCardDoesNotExistsException
        extends ServiceException {

    /** Id in db */
    private final Long id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public MedicalCardDoesNotExistsException(Long id) {
        this.id = id;
    }

    /**
     * Id getter
     * @return id
     */
    public Long getId() {
        return id;
    }
}
