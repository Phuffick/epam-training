package com.epam.service.implementation.procedure.exception;

import com.epam.service.exception.ServiceException;

/**
 * ProcedureRequiredCountConsumedException exception definition.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ProcedureRequiredCountConsumedException
        extends ServiceException {

    /** Required count consumed in db */
    private Integer id;

    /**
     * Default constructor
     * @param id of exception cause obj
     */
    public ProcedureRequiredCountConsumedException(Integer id) {
        this.id = id;
    }

    /**
     * Required count consumed getter
     * @return id
     */
    public Integer getId() {
        return id;
    }
}
