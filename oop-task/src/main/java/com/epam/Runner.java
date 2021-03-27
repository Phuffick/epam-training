package com.epam;

import com.epam.comparators.applience.connectable.PowerComparator;
import com.epam.entities.ConnectionsManager;
import com.epam.entities.applience.devices.Device;
import com.epam.entities.connector.Socket;
import com.epam.filters.applience.device.ManufacturerNameFilter;
import com.epam.filters.connectable.PowerFilter;
import com.epam.obcectcreators.device.DeviceCreator;
import com.epam.obcectcreators.socket.SocketCreator;
import com.epam.parser.DevicesParser;
import com.epam.parser.SocketsParser;

import java.util.ArrayList;

/**
 * Main class
 *
 * @version    1.0.0 16 March 2021
 * @author     Belyack Maxim
 */
public class Runner {

    /**
     * Create devices method from text file
     * @param fileName name of text file
     * @return devices list
     */
    public static ArrayList<Device> createDevices(String fileName) {
        ArrayList<ArrayList<String>> devicesParams
                = new DevicesParser(fileName).parse();
        ArrayList<Device> devices = new ArrayList<>();
        for (ArrayList<String> params:
             devicesParams) {
            devices.add(new DeviceCreator(params).create());
        }
        return devices;
    }

    /**
     * Create sockets method from text file
     * @param fileName name of text file
     * @return sockets list
     */
    public static ArrayList<Socket> createSockets(String fileName) {
        ArrayList<ArrayList<String>> socketsParams
                = new SocketsParser(fileName).parse();
        ArrayList<Socket> sockets = new ArrayList<>();
        for (ArrayList<String> params:
                socketsParams) {
            sockets.add(new SocketCreator(params).create());
        }
        return sockets;
    }

    /**
     * Main method
     * @param args standard cmd arguments
     */
    public static void main(String[] args) {
        try {
            ArrayList<Device> devices
                    = createDevices("src\\main\\java\\com\\epam\\resources\\devices.txt");
            ArrayList<Socket> sockets
                    = createSockets("src\\main\\java\\com\\epam\\resources\\sockets.txt");

            ConnectionsManager connectionsManager
                    = new ConnectionsManager(sockets.size(), sockets);
            for (Device device : devices) {
                connectionsManager.plugDevice(device);
            }
            System.out.println(connectionsManager.toString());

            int consumptionPower = connectionsManager.calculatePower();
            System.out.println(
                    "Total consumption power: " + consumptionPower);

            connectionsManager.sort(new PowerComparator());
            System.out.println("After sort:\n"
                    + connectionsManager.toString());

            ArrayList<Device> foundDevices = new ArrayList<>();
            connectionsManager.findDevice(foundDevices,
                    new PowerFilter(20),
                    new ManufacturerNameFilter("Philips"));
            System.out.println("Found results:\n");
            for (Device device : foundDevices) {
                System.out.println(device.toString());
            }
        } catch (Exception exception) {
            System.out.println("Exception occurred: "
                    + exception.getMessage());
        }
    }
}
