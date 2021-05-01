package com.epam.xml.sax.handles;

import org.apache.log4j.BasicConfigurator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * XML handler abstract class
 * Default handler and tariffs xml handler mediator
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public abstract class XmlHandler extends DefaultHandler {

    /** Logger */
    protected static final org.apache.log4j.Logger logger
            = org.apache.log4j.Logger.getLogger(XmlHandler.class);

    /**
     * Conductor initialising logger
     */
    public XmlHandler() {
        BasicConfigurator.configure();
    }

    /**
     * Warning handle method
     * @param exception to log up
     */
    @Override
    public void warning(SAXParseException exception) {
        logger.warn("Warning: " + exception + "\n");
    }

    /**
     * Error handle method
     * @param exception to log up
     * @throws SAXException to re-throw
     */
    @Override
    public void error(SAXParseException exception)
            throws SAXException {
        String message = "Error: " + exception  + "\n";
        logger.error(message);
        throw new SAXException(message);
    }

    /**
     * Fatal error handle method
     * @param exception to log up
     * @throws SAXException to re-throw
     */
    @Override
    public void fatalError(SAXParseException exception)
            throws SAXException {
        String message = "Fatal Error: " + exception  + "\n";
        logger.fatal(message);
        throw new SAXException(message);
    }
}
