package com.epam.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.BasicConfigurator;
import org.xml.sax.SAXException;

/**
 * Tariffs XML validator definition class
 * Handles validation tariffs xml to xsd
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class XmlValidator {

    /** Logger */
    protected static final org.apache.log4j.Logger logger
            = org.apache.log4j.Logger.getLogger(XmlValidator.class);

    /** Schema file (.xsd) */
    public final File SCHEMA_FILE;

    /** Xml file (.xml) */
    private File xmlFile;

    /**
     * Constructor
     * @param schemaFile .xsd file
     * @param xmlFile .xml file
     */
    public XmlValidator(File schemaFile,
                        File xmlFile) {
        BasicConfigurator.configure();
        this.SCHEMA_FILE = schemaFile;
        this.xmlFile = xmlFile;
    }

    /**
     * Xml file to validate setter
     * @param xmlFile .xml file
     */
    public void setXlmFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    /**
     * Checks if xml file is valid to .xsd file
     * @return is xml valid
     * @throws IOException throws at validate method
     */
    public boolean validate() throws IOException {
        SchemaFactory factory = SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(SCHEMA_FILE);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException e) {
            logger.error("Validation failed: " + e.getMessage());
            return false;
        }
        return true;
    }
}

