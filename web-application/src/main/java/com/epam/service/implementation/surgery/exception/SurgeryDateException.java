package com.epam.service.implementation.surgery.exception;

import com.epam.service.exception.ServiceException;

import java.util.Date;

/**
 * SurgeryDateException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SurgeryDateException extends ServiceException {

    /** Invalid date */
    private Date date;

    /**
     * Default constructor
     */
    public SurgeryDateException() {}

    /**
     * Default constructor
     * @param date incorrect
     */
    public SurgeryDateException(Date date) {
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
