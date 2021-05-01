package com.epam.parsers;

import com.epam.entities.Tariff;
import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;
import com.epam.xml.sax.XmlParserSax;
import com.epam.xml.sax.handles.read.TariffsXmlReadHandler;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TariffsXmlParserSaxTest {

    @Test
    public void oneTariffTest() throws IOException,
            ParserConfigurationException, SAXException {
        XmlParserSax xmlParserSax = new XmlParserSax();
        List<Tariff> parsedTariffsSax = xmlParserSax.parse(
                new File("src"  + File.separator +  "test"
                        + File.separator +  "java" + File.separator
                        +  "com" + File.separator + "epam"
                        + File.separator + "resources"
                        + File.separator +"tariffs.xml"),
                new TariffsXmlReadHandler());
        Tariff expected = new Tariff();
        expected.setId("x0001");
        expected.setType("F");
        expected.setOperator("MTC");
        expected.setMonthPayroll(Price.parsePrice("25.48"));
        expected.setInNetworkCallsPrice(Price.parsePrice("0.1"));
        expected.setOutNetworkCallsPrice(Price.parsePrice("0.2"));
        expected.setLandlinesCallsPrice(Price.parsePrice("0.2"));
        expected.setSmsPrice(Price.parsePrice("0.2"));
        expected.getFavoriteCellNumbers().add("+375(29)432-23-23");
        expected.getFavoriteCellNumbers().add("+375(29)543-27-45");
        expected.setTarifficationType(
                TarifficationType.TWELVE_SECONDS_TARIFFICATION);
        expected.setConnectionPrice(Price.parsePrice("0"));
        Assert.assertEquals(expected, parsedTariffsSax.get(0));
    }
}
