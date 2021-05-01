package com.epam.xml.sax.handles.read;

import com.epam.entities.Tariff;
import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

/**
 * Tariffs XML handler definition class
 * Handles the way of parsing tariffs xml
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class TariffsXmlReadHandler extends XmlReadHandler {

    /** Already parsed tariffs */
    private final List<Tariff> tariffs = new ArrayList<>();

    /** Temporarily tariff */
    private Tariff tariff = null;

    /** Tag value in string */
    private StringBuilder tagValue = new StringBuilder();

    /**
     * Already parsed tariffs getter
     * @return parsed tariffs
     */
    @Override
    public List<Tariff> getParsedResultList() {
        return tariffs;
    }

    /**
     * Receive notification of the start of an element
     * @param uri the namespace URI
     * @param localName the local name (without prefix)
     * @param qName the qualified name (with prefix)
     * @param attributes the attributes attached to the element
     * @throws SAXException any SAXException
     */
    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        if ("tariff".equals(qName)) {
            tariff = new Tariff();
            tariff.setId(attributes.getValue("id"));
        }
        tagValue = new StringBuilder();
    }

    /**
     * Receive notification of the end of an element
     * @param uri the namespace URI
     * @param localName the local name (without prefix)
     * @param qName the qualified name (with prefix)
     * @throws SAXException any SAXException
     */
    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        if ("tariff".equals(qName)) {
            tariffs.add(tariff);
        } else if ("type".equals(qName)) {
            tariff.setType(tagValue.toString());
        } else if ("operator".equals(qName)) {
            tariff.setOperator(tagValue.toString());
        } else if ("payroll".equals(qName)) {
            tariff.setMonthPayroll(Price.parsePrice(
                    tagValue.toString()));
        } else if ("inNetworkCalls".equals(qName)) {
            tariff.setInNetworkCallsPrice(
                    Price.parsePrice(
                            tagValue.toString()));
        } else if ("outNetworkCalls".equals(qName)) {
            tariff.setOutNetworkCallsPrice(
                    Price.parsePrice(
                            tagValue.toString()));
        } else if ("landlinesCalls".equals(qName)) {
            tariff.setLandlinesCallsPrice(
                    Price.parsePrice(
                            tagValue.toString()));
        } else if ("smsPrice".equals(qName)) {
            tariff.setSmsPrice(
                    Price.parsePrice(
                            tagValue.toString()));
        } else if ("favoriteNumber".equals(qName)) {
            tariff.getFavoriteCellNumbers().add(tagValue.toString());
        } else if ("tariffication".equals(qName)) {
            tariff.setTarifficationType(TarifficationType.valueOf(
                    tagValue.toString()));
        } else if ("rateForTariffConnection"
                .equals(qName)) {
            tariff.setConnectionPrice(Price.parsePrice(
                    tagValue.toString()));
        }
    }

    /**
     * Receive notification of character data inside an element
     * @param ch the characters
     * @param start the start position in the character array
     * @param length the number of characters to use from the
     *               character array
     * @throws SAXException any SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tagValue.append(new String(ch, start, length));
    }
}
