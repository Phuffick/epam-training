package com.epam;

import com.epam.comparators.PayrollComparator;
import com.epam.entities.Tariff;
import com.epam.xml.sax.XmlParserSax;
import com.epam.xml.sax.handles.read.TariffsXmlReadHandler;
import com.epam.xml.XmlValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Main class
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class Runner {

    /**
     * Prints array list
     * @param <T> any type
     * @param list of elements to print
     */
    public static <T> void printArrayList(List<T> list) {
        System.out.print("[ ");
        for (T element : list) {
            System.out.print("{ " + element.toString() + " }");
            if (element == list.get(list.size() - 1)) {
                System.out.print(" ]");
            } else {
                System.out.print(",\n");
            }
        }
        System.out.print("\n\n");
    }

    /**
     * Main method
     * @param args standard cmd arguments
     */
    public static void main(String[] args) throws IOException,
            ParserConfigurationException, SAXException {
        boolean isValid = new XmlValidator(
                new File("resources"
                        + File.separator + "tariffs.xsd"),
                new File("resources"
                        + File.separator + "tariffs.xml"))
                .validate();
        if (!isValid) {
            System.out.println("File .xml is invalid to inputted .xsd");
            return;
        }
        XmlParserSax xmlParserSax = new XmlParserSax();
        List<Tariff> parsedTariffsSax = xmlParserSax.parse(
                new File("resources" + File.separator +"tariffs.xml"),
                new TariffsXmlReadHandler());
        System.out.println("Before sort:");
        printArrayList(parsedTariffsSax);
        Collections.sort(parsedTariffsSax, new PayrollComparator());
        System.out.println("After sort:");
        printArrayList(parsedTariffsSax);
    }
}
