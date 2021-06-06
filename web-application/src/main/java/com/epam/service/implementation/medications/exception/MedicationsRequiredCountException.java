package com.epam.service.implementation.medications.exception;

import com.epam.service.exception.ServiceException;

/**
 * MedicationsRequiredCountException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationsRequiredCountException
        extends ServiceException {

    /** Required count in db */
    private Integer id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public MedicationsRequiredCountException(Integer id) {
        this.id = id;
    }

    /**
     * Required count getter
     * @return id
     */
    public Integer getId() {
        return id;
    }
}
