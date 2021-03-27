package com.epam;

import com.epam.comparators.applience.connectable.PowerComparator;
import com.epam.entities.ConnectionsManager;
import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.entities.connector.Socket;
import com.epam.filters.applience.device.ManufacturerNameFilter;
import com.epam.filters.connectable.PowerFilter;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Connections manager unit test.
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class ConnectionsManagerTest {

    /** Connection manager for tests */
    private ConnectionsManager connectionsManager;

    /**
     * Connections manager initialisation
     */
    @Before
    public void connectionsManagerInit() {
        try {
            Device[] devices = {
                    new CoffeeMaker(20,false, 34500,
                    EnergyConsumptionClasses.A, 3, "Philips",
                    PlugTypes.TYPE_B, new GregorianCalendar(2017,8, 01),
                    1800, 250),
                    new CoffeeMaker(10,false, 34500,
                            EnergyConsumptionClasses.A, 3, "Philips",
                            PlugTypes.TYPE_B, new GregorianCalendar(2017,8, 01),
                            1800, 250)
            };
            Socket[] sockets
                    = { new Socket(null, PlugTypes.TYPE_B, 200),
                        new Socket(null, PlugTypes.TYPE_B, 200) };
            connectionsManager
                    = new ConnectionsManager(sockets.length,
                    new ArrayList(Arrays.asList(sockets)));
            for (Device device : devices) {
                connectionsManager.plugDevice(device);
            }
        } catch (Exception exception) {
            fail();
        }
    }

    /**
     * Test power calculation
     */
    @Test
    public void calculateTotalPower() {
        try {
            assertEquals(30, connectionsManager.calculatePower());
        } catch (Exception exception) {
            fail();
        }
    }

    /**
     * Test sort
     */
    @Test
    public void sortTest() {
        try {
            ConnectionsManager.Connection firstConnection
                    = new ConnectionsManager.Connection(
                    new Socket(null, PlugTypes.TYPE_B, 200, 10),
                    new CoffeeMaker(10,false, 34500,
                            EnergyConsumptionClasses.A, 3, "Philips",
                            PlugTypes.TYPE_B,
                            new GregorianCalendar(2017,8, 01),
                            1800, 250));
            ConnectionsManager.Connection secondConnection
                    = new ConnectionsManager.Connection(
                    new Socket(null, PlugTypes.TYPE_B, 200, 20),
                    new CoffeeMaker(20,false, 34500,
                            EnergyConsumptionClasses.A, 3, "Philips",
                            PlugTypes.TYPE_B,
                            new GregorianCalendar(2017,8, 01),
                            1800, 250));
            ConnectionsManager.Connection[] expectedConnections
                    = {firstConnection, secondConnection};
            for (ConnectionsManager.Connection connection
                    : expectedConnections) {
                connection.getSocket().setDevice(
                        connection.getDevice());
            }
            connectionsManager.sort(new PowerComparator());
            assertArrayEquals(expectedConnections,
                    connectionsManager.getConnections().toArray());
        } catch (Exception exception) {
            fail();
        }
    }

    /**
     * Test find
     */
    @Test
    public void findTest() {
        try {
            Device[] expected = { new CoffeeMaker(20,false, 34500,
                    EnergyConsumptionClasses.A, 3, "Philips",
                    PlugTypes.TYPE_B,
                    new GregorianCalendar(2017,8, 01),
                    1800, 250)};
            for (Device device : expected) {
                device.setPluggedIn(true);
            }
            ArrayList<Device> foundDevices = new ArrayList<>();
            connectionsManager.findDevice(foundDevices,
                    new PowerFilter(20),
                    new ManufacturerNameFilter("Philips"));
            assertArrayEquals(expected, foundDevices.toArray());
        } catch (Exception exception) {

        }
    }
}
