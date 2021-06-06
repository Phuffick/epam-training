package com.epam.controller.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * SessionEventsListener definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SessionEventsListener
        implements HttpSessionAttributeListener {

    private static final Logger LOGGER
            = LogManager.getLogger(SessionListener.class);

    /**
     * Attribute added listener
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        LOGGER.info("Attribute added, name - {}, value {}",
                event.getName(), event.getValue());
    }

    /**
     * Attribute removed listener
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        LOGGER.info("Attribute removed, name - {}, value {}",
                event.getName(), event.getValue());
    }

    /**
     * Attribute replaced listener
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        LOGGER.info("Attribute replaced, name - {}, value {}",
                event.getName(), event.getValue());
    }
}
