package com.epam.xml.sax;

import com.epam.xml.sax.handles.read.XmlReadHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Tariffs XML parser STAX definition class
 * Reads file from xml file using SAX parser
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class XmlParserSax {

    /**
     * Read method
     * @param xmlFileToParse xml file to parse
     * @param xmlHandler xml parse handler
     * @return list of parsed entities
     * @throws IOException throws at SAXParser
     * @throws SAXException throws at SAXParser
     * @throws ParserConfigurationException throws at SAXParserFactory
     */
    public <T> List<T> parse(File xmlFileToParse,
                             XmlReadHandler xmlHandler)
            throws IOException, SAXException,
            ParserConfigurationException {
        SAXParserFactory saxParserFactory
                = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(xmlFileToParse, xmlHandler);
        return xmlHandler.getParsedResultList();
    }
}