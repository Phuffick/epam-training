package com.epam.service.implementation.diagnosis.exception;

import com.epam.service.exception.ServiceException;

import java.util.Date;

/**
 * DiagnosisDateException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DiagnosisDateException extends ServiceException {

    /** Invalid date */
    private Date date;

    /**
     * Default constructor
     */
    public DiagnosisDateException() {}

    /**
     * Default constructor
     * @param date incorrect
     */
    public DiagnosisDateException(Date date) {
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
