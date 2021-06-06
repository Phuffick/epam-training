package com.epam.service.implementation.procedure.exception;

import com.epam.service.exception.ServiceException;

import java.util.Date;

/**
 * SurgeryDateException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ProcedureDateException extends ServiceException {

    /** Invalid date */
    private Date date;

    /**
     * Default constructor
     */
    public ProcedureDateException() {}

    /**
     * Default constructor
     * @param date incorrect
     */
    public ProcedureDateException(Date date) {
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
