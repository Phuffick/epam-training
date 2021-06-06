package com.epam.service.implementation.medicalcard.exception;

import com.epam.service.exception.ServiceException;

import java.util.Calendar;

/**
 * DischargeDateSettingException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DischargeDateSettingException
        extends ServiceException {

    /** Date from form */
    private final Calendar date;

    /**
     * Default constructor
     * @param date of exception cause obj
     */
    public DischargeDateSettingException(Calendar date) {
        this.date = date;
    }

    /**
     * Date getter
     * @return date
     */
    public Calendar getDate() {
        return date;
    }
}
