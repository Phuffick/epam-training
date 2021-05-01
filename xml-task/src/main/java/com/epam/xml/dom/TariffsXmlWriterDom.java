package com.epam.xml.dom;

import com.epam.entities.Tariff;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Tariff XML writer DOM definition class
 * Writes data to XML format
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class TariffsXmlWriterDom {

    /**
     * Creation tariff method
     * @param doc to write
     * @param tariff to edit
     * @return node to add
     */
    private Node createTariff(Document doc, Tariff tariff) {
        Element tariffElement = doc.createElement("tariff");
        tariffElement.setAttribute("id", tariff.getId());
        tariffElement.appendChild(
                createTariffElement(doc, "type", tariff.getType()));
        tariffElement.appendChild(
                createTariffElement(doc,
                        "operator", tariff.getOperator()));
        tariffElement.appendChild(
                createTariffElement(doc,
                        "payroll", tariff.getMonthPayroll()));
        Element callPricesElement = doc.createElement("callPrices");
        callPricesElement.appendChild(
                createTariffElement(doc,
                        "inNetworkCalls",
                        tariff.getInNetworkCallsPrice()));
        callPricesElement.appendChild(
                createTariffElement(doc,
                        "outNetworkCalls",
                        tariff.getOutNetworkCallsPrice()));
        callPricesElement.appendChild(
                createTariffElement(doc,
                        "landlinesCalls",
                        tariff.getLandlinesCallsPrice()));
        tariffElement.appendChild(callPricesElement);
        tariffElement.appendChild(
                createTariffElement(doc,
                        "smsPrice",
                        tariff.getSmsPrice()));
        Element parametersElement = doc.createElement("parameters");
        for (String cellNumber
                : tariff.getFavoriteCellNumbers()) {
            parametersElement.appendChild(createTariffElement(doc,
                   "favoriteNumber", cellNumber));
        }
        parametersElement.appendChild(
                createTariffElement(doc,
                        "tariffication",
                        tariff.getTarifficationType()));
        parametersElement.appendChild(
                createTariffElement(doc,
                        "rateForTariffConnection",
                        tariff.getConnectionPrice()));
        tariffElement.appendChild(parametersElement);
        return tariffElement;
    }

    /**
     * Creates tariff element
     * @param document to write
     * @param name tag name
     * @param value tag value
     * @return node
     */
    private Node createTariffElement(Document document, String name,
                                     String value) {
        Element node = document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }

    /**
     * Dom tariff write method
     * @param tariffs to write
     * @param file to write
     * @throws FileNotFoundException if file is not found
     * @throws XMLStreamException
     */
    public void write(List<Tariff> tariffs, File file)
            throws ParserConfigurationException,
            TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();
        Element root = document.createElementNS(
                "http://www.example.org/tariffs", "tariffs");
        document.appendChild(root);
        for (Tariff tariff : tariffs) {
            root.appendChild(createTariff(document, tariff));
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult console = new StreamResult(System.out);
        StreamResult xmlFile = new StreamResult(file);
        transformer.transform(source, console);
        transformer.transform(source, xmlFile);
    }
}
