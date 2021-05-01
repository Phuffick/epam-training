package com.epam.xml.stax;

import com.epam.entities.Tariff;
import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;
import org.apache.log4j.BasicConfigurator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tariffs XML parser STAX definition class
 * Reads file from xml file using StAX parser
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class TariffsXmlParserStax {

    /** Logger */
    protected static final org.apache.log4j.Logger logger
            = org.apache.log4j.Logger.getLogger(TariffsXmlParserStax.class);

    /**
     * Conductor initialising logger
     */
    public TariffsXmlParserStax() {
        BasicConfigurator.configure();
    }

    /**
     * Read method
     * @param xmlFile to read
     * @return list of parsed entities
     */
    public List<Tariff> parse(File xmlFile) {
        XMLStreamReader reader = null;
        try {
            List<Tariff> tariffs = new ArrayList<>();
            Tariff tariff = null;
            XMLInputFactory factory = XMLInputFactory.newFactory();
            reader = factory.createXMLStreamReader(new FileInputStream(xmlFile));
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("tariff".equals(tagName)) {
                            tariff = new Tariff();
                            tariff.setId(reader
                                    .getAttributeValue(null, "id"));
                        } else if ("type".equals(tagName)) {
                            tariff.setType(reader.getElementText());
                        } else if ("operator".equals(tagName)) {
                            tariff.setOperator(
                                    reader.getElementText());
                        } else if ("payroll".equals(tagName)) {
                            tariff.setMonthPayroll(Price.parsePrice(
                                    reader.getElementText()));
                        } else if ("inNetworkCalls".equals(tagName)) {
                            tariff.setInNetworkCallsPrice(
                                    Price.parsePrice(
                                            reader.getElementText()));
                        } else if ("outNetworkCalls".equals(tagName)) {
                            tariff.setOutNetworkCallsPrice(
                                    Price.parsePrice(
                                            reader.getElementText()));
                        } else if ("landlinesCalls".equals(tagName)) {
                            tariff.setLandlinesCallsPrice(
                                    Price.parsePrice(
                                            reader.getElementText()));
                        } else if ("smsPrice".equals(tagName)) {
                            tariff.setSmsPrice(
                                    Price.parsePrice(
                                            reader.getElementText()));
                        } else if ("favoriteNumber".equals(tagName)) {
                            tariff.getFavoriteCellNumbers().add(
                                    reader.getElementText());
                        } else if ("tariffication".equals(tagName)) {
                            tariff.setTarifficationType(
                                    TarifficationType.valueOf(
                                            reader.getElementText()));
                        } else if ("rateForTariffConnection"
                                .equals(tagName)) {
                            tariff.setConnectionPrice(
                                    Price.parsePrice(
                                            reader.getElementText()));
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("tariff".equals(tagName)) {
                            tariffs.add(tariff);
                        }
                        break;
                    }
                }
            }
            return tariffs;
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.warn("Exception caught:", e);
            return null;
        } finally {
            try {
                reader.close();
            } catch (Exception ignored) {
            }
        }
    }
}
