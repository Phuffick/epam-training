package com.epam.service.implementation.doctor.exception;

import com.epam.service.exception.ServiceException;

/**
 * DaoException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DoctorDoesNotExistsException
        extends ServiceException {

    private Long id;

    public DoctorDoesNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
