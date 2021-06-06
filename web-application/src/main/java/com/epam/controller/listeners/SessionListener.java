package com.epam.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListener definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER
            = LogManager.getLogger(SessionListener.class);

    /**
     * Session creating listener
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("Session created, free connections - {}",
                se.getSession().getId());
    }

    /**
     * Session destroying listener
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("Session destroyed, free connections - {}",
                se.getSession().getId());
    }
}
