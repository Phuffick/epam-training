package com.epam;

import static com.epam.Runner.createDevices;
import static com.epam.Runner.createSockets;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.entities.connector.Socket;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Multiple objects creation unit test.
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class MultipleObjectsCreationTest {

    /**
     * Test devices creation
     */
    @Test
    public void createDevicesTest() {
        Device[] expected = { new CoffeeMaker(20,false, 34500,
                EnergyConsumptionClasses.A, 3, "Philips",
                PlugTypes.TYPE_B, new GregorianCalendar(2017,8, 01),
                1800, 250),
                new CoffeeMaker(10,false, 34500,
                        EnergyConsumptionClasses.A, 3, "Philips",
                        PlugTypes.TYPE_B, new GregorianCalendar(2017,8, 01),
                        1800, 250)
        };
        try {
            ArrayList<Device> devices
                    = createDevices("src\\test\\java\\com\\epam"
                                    + "\\testresouses\\devices.txt");
            assertArrayEquals(expected, devices.toArray());
        } catch (Exception exception) {
            fail();
        }
    }

    /**
     * Test sockets creation
     */
    @Test
    public void createSocketsTest() {
        Socket[] expected
                = { new Socket(null, PlugTypes.TYPE_B, 200) };
        try {
            ArrayList<Socket> sockets
                    = createSockets("src\\test\\java\\com\\epam"
                                    + "\\testresouses\\sockets.txt");
            assertArrayEquals(expected, sockets.toArray());
        } catch (Exception exception) {
            fail();
        }
    }
}
