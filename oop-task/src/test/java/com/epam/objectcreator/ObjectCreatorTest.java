package com.epam.objectcreator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.obcectcreators.device.kitchen.CoffeeMakerCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import org.junit.Test;

/**
 * Unit test object creator test.
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class ObjectCreatorTest {
    /**
     * Coffee maker creator test
     */
    @Test
    public void earphonesCreatorTest() {
        String[] earphonesParams
                = { "CoffeeMaker","15","false", "34500", "A", "3",
                    "Philips", "TYPE_B", "2017.08.01", "1800", "250"};
        CoffeeMakerCreator coffeeMakerCreator
                = new CoffeeMakerCreator(
                        new ArrayList<>(Arrays.asList(earphonesParams)
                        )
        );
        CoffeeMaker expected
                = new CoffeeMaker(15,false, 34500,
                EnergyConsumptionClasses.A, 3, "Philips",
                PlugTypes.TYPE_B, new GregorianCalendar(2017,8, 01),
                1800, 250);
        assertEquals(expected, coffeeMakerCreator.create());
    }
}
