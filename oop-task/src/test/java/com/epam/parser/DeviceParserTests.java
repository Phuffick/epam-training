package com.epam.parser;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit test device parser.
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class DeviceParserTests {

    /**
     * Test coffee maker
     * CoffeeMaker;20;false;34500;A;3;Philips;
     * TYPE_B;2017.08.01;1800;250
     */
    @Test
    public void coffeeMakerTest() {
        String [][] params = {
                {
                    "CoffeeMaker", "20", "false", "34500", "A", "3",
                    "Philips", "TYPE_B", "2017.08.01", "1800", "250"
                },
                {
                        "CoffeeMaker", "10", "false", "34500", "A", "3",
                        "Philips", "TYPE_B", "2017.08.01", "1800", "250"
                }
        };
        ArrayList<ArrayList<String>> parsedDevices
                = new DevicesParser("src\\test\\java\\com\\epam\\testresouses\\devices.txt")
                .parse();
        for (int i = 0; i < params.length; i++) {
            assertArrayEquals(params[i], parsedDevices.get(i).toArray());
        }
    }
}
