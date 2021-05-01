package com.epam.xml.sax.handles.read;

import com.epam.xml.sax.handles.XmlHandler;

import java.util.List;

/**
 * Tariffs XML reader handle abstract class
 * Handles the way of parsing xml
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public abstract class XmlReadHandler extends XmlHandler {

    /**
     * Parse result getter
     * @return parsed result
     */
    public abstract <T> List<T> getParsedResultList();
}
