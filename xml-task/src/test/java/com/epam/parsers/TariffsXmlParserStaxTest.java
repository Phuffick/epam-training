package com.epam.parsers;

import com.epam.entities.Tariff;
import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;
import com.epam.xml.stax.TariffsXmlParserStax;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class TariffsXmlParserStaxTest {

    @Test
    public void oneTariffTest() {
        TariffsXmlParserStax tariffsXmlParserStax
                = new TariffsXmlParserStax();
        List<Tariff> parsedTariffsStax = tariffsXmlParserStax.parse(
                new File("src"  + File.separator +  "test"
                        + File.separator +  "java" + File.separator
                        +  "com" + File.separator + "epam"
                        + File.separator + "resources"
                        + File.separator +"tariffs.xml"));
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
        Assert.assertEquals(expected, parsedTariffsStax.get(0));
    }
}
