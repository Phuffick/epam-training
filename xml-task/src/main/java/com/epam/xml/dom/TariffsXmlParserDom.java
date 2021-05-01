package com.epam.xml.dom;

import com.epam.entities.Tariff;
import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;
import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tariff XML parser DOM definition class
 * Writes data to XML format
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class TariffsXmlParserDom {

    /** Logger */
    protected static final org.apache.log4j.Logger logger
            = org.apache.log4j.Logger.getLogger(TariffsXmlParserDom.class);

    /**
     * Conductor initialising logger
     */
    public TariffsXmlParserDom() {
        BasicConfigurator.configure();
    }

    /**
     * Tariff element parser
     * @param node to parse
     * @return parsed tariff
     */
    private Tariff getTariff(Node node) {
        Tariff tariff = new Tariff();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            tariff.setId(element.getAttribute("id"));
            tariff.setType(getTagValue("type", element));
            tariff.setOperator(getTagValue("operator", element));
            tariff.setMonthPayroll(
                    Price.parsePrice(getTagValue("payroll", element)));
            tariff.setInNetworkCallsPrice(
                    Price.parsePrice(
                            getTagValue("inNetworkCalls", element)));
            tariff.setOutNetworkCallsPrice(
                    Price.parsePrice(
                            getTagValue("outNetworkCalls", element)));
            tariff.setLandlinesCallsPrice(
                    Price.parsePrice(
                            getTagValue("landlinesCalls", element)));
            tariff.setSmsPrice(
                    Price.parsePrice(
                            getTagValue("smsPrice", element)));
            NodeList favoriteNumbers
                    = element.getElementsByTagName("favoriteNumber");
            for (int i = 0; i < favoriteNumbers.getLength(); i++) {
                tariff.getFavoriteCellNumbers().add(favoriteNumbers.item(i)
                        .getTextContent());
            }
            tariff.setTarifficationType(TarifficationType.valueOf(
                    getTagValue("tariffication", element)));
            tariff.setConnectionPrice(
                    Price.parsePrice(
                            getTagValue("rateForTariffConnection",
                                    element)));
        }
        return tariff;
    }

    /**
     * Tag value parser
     * @param tag name to find tag
     * @param element element to parse tag
     * @return parsed result in string
     */
    private String getTagValue(String tag, Element element) {
        NodeList nodeList
                = element.getElementsByTagName(tag).item(0)
                .getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    /**
     * Read method
     * @param xmlFile to read
     * @return list of parsed entities
     */
    public List<Tariff> parse(File xmlFile) {
        List<Tariff> tariffs = new ArrayList<>();
        DocumentBuilderFactory factory
                = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            if (!"tariffs".equals(
                    document.getDocumentElement().getNodeName())) {
                throw new IllegalArgumentException(
                        "File have not tariffs to parse.");
            }
            NodeList nodeList
                    = document.getElementsByTagName("tariff");
            for (int i = 0; i < nodeList.getLength(); i++) {
                tariffs.add(getTariff(nodeList.item(i)));
            }
            return tariffs;
        } catch (SAXException | IOException
                | ParserConfigurationException e) {
            logger.warn("Exception caught:", e);
            return null;
        }
    }
}
