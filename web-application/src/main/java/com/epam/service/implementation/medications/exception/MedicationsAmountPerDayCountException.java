package com.epam.service.implementation.medications.exception;

import com.epam.service.exception.ServiceException;

/**
 * MedicationsAmountPerDayCountException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationsAmountPerDayCountException
        extends ServiceException {

    /** Amount per day count in db */
    private Integer id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public MedicationsAmountPerDayCountException(Integer id) {
        this.id = id;
    }

    /**
     * Amount per day count getter
     * @return id
     */
    public Integer getId() {
        return id;
    }
}
