package com.epam.controller.commands;

import com.epam.controller.Direct;
import com.epam.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public abstract class Command {

    /** Service factory */
    private ServiceFactory serviceFactory;

    /**
     * Service factory getter
     * @return service factory
     */
    public final ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    /**
     * Service factory setter
     * @param serviceFactory to set up
     */
    public final void setServiceFactory(
            ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    /** Abstract execute method */
    abstract public Direct execute(HttpServletRequest req,
                                   HttpServletResponse resp)
            throws ServletException, IOException;
}
