package com.epam.service.implementation.diagnosis.exception;

import com.epam.service.exception.ServiceException;

/**
 * DiagnosisDoesNotExistsException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DiagnosisDoesNotExistsException
        extends ServiceException {

    /** Id in db */
    private Long id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public DiagnosisDoesNotExistsException(Long id) {
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
