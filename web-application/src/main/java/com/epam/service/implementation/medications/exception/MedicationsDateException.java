package com.epam.service.implementation.medications.exception;

import com.epam.service.exception.ServiceException;

import java.util.Date;

/**
 * MedicationsDateException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationsDateException extends ServiceException {

    /** Invalid date */
    private Date date;

    /**
     * Default constructor
     */
    public MedicationsDateException() {}

    /**
     * Default constructor
     * @param date incorrect
     */
    public MedicationsDateException(Date date) {
        this.date = date;
    }

    /**
     * Date getter
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
