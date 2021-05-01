package com.epam.xml.stax;

import com.epam.entities.Tariff;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Tariff XML writer STAX definition class
 * Writes data to XML format
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class TariffsXmlWriterStax {

    /** XML stream writer */
    private XMLStreamWriter writer = null;

    /**
     * Write simple element with localName and value
     * @param localName local name (tag name)
     * @param characters characters (tag value)
     * @throws XMLStreamException
     */
    private void writeElement(String localName, String characters)
            throws XMLStreamException {
        writer.writeStartElement(localName);
        writer.writeCharacters(characters);
        writer.writeEndElement();
    }

    /**
     * Stax tariff write method
     * @param tariffs to write
     * @param file to write
     * @throws FileNotFoundException if file is not found
     * @throws XMLStreamException
     */
    public void write(List<Tariff> tariffs, File file)
            throws FileNotFoundException, XMLStreamException {
        try {
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            writer = factory.createXMLStreamWriter(
                    new FileOutputStream(file), "UTF-8");
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement("tariffs");
            writer.writeAttribute("xmlns:xsi",
                    "http://www.w3.org/2001/XMLSchema-instance");
            writer.writeAttribute("xmlns",
                    "http://www.example.org/tariffs");
            writer.writeAttribute("xsi:schemaLocation",
                    "http://www.example.org/tariffs tariffs.xsd");
            for (Tariff tariff : tariffs) {
                writer.writeCharacters("\n\t");
                writer.writeStartElement("tariff");
                writer.writeAttribute("id", tariff.getId());
                writer.writeCharacters("\n\t\t");
                writeElement("type", tariff.getType());
                writer.writeCharacters("\n\t\t");
                writeElement("operator", tariff.getOperator());
                writer.writeCharacters("\n\t\t");
                writeElement("payroll", tariff.getMonthPayroll());
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("callPrices");
                writer.writeCharacters("\n\t\t\t");
                writeElement("inNetworkCalls",
                        tariff.getInNetworkCallsPrice());
                writer.writeCharacters("\n\t\t\t");
                writeElement("outNetworkCalls",
                        tariff.getOutNetworkCallsPrice());
                writer.writeCharacters("\n\t\t\t");
                writeElement("landlinesCalls",
                        tariff.getLandlinesCallsPrice());
                writer.writeCharacters("\n\t\t");
                writer.writeEndElement();
                writer.writeCharacters("\n\t\t");
                writeElement("smsPrice", tariff.getSmsPrice());
                writer.writeCharacters("\n\t\t");
                writer.writeStartElement("parameters");
                for (String cellNumber : tariff.getFavoriteCellNumbers()) {
                    writer.writeCharacters("\n\t\t\t");
                    writeElement("favoriteNumber", cellNumber);
                }
                writer.writeCharacters("\n\t\t\t");
                writeElement("tariffication",
                        tariff.getTarifficationType());
                writer.writeCharacters("\n\t\t\t");
                writeElement("rateForTariffConnection",
                        tariff.getConnectionPrice());
                writer.writeCharacters("\n\t\t");
                writer.writeEndElement();
                writer.writeCharacters("\n\t");
                writer.writeEndElement();
            }
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeEndDocument();
        } finally {
            try {
                writer.close();
            } catch (Exception ignored) {
            }
        }
    }
}