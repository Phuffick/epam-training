package com.epam.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.connector.Socket;
import com.epam.filters.Filter;

/**
 * Connections manager definition class
 *
 * @version    1.0.0 15 March 2021
 * @author     Belyack Maxim
 */
public class ConnectionsManager {

    /** Connections array */
    private ArrayList<Connection> connections = new ArrayList<>();

    /** Max connection count */
    private final int maxConnectionsCount;

    /** Constructor */
    public ConnectionsManager(int maxConnectionsCount,
                       ArrayList<Socket> sockets) {
        if (maxConnectionsCount < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: max connections count.");
        }
        this.maxConnectionsCount = maxConnectionsCount;
        for (Socket socket : sockets) {
            connections.add(new Connection(socket));
        }
    }

    /**
     * Add socket method
     * @param socket socket to set up
     */
    public void addSocket(Socket socket) {
        if (maxConnectionsCount <= connections.size()) {
            throw new IllegalStateException(
                    "Exception: have max count of connections.");
        }
        connections.add(new Connection(socket));
    }

    /**
     * Add device method
     * @param device device to set up
     */
    public void plugDevice(Device device) {
        Connection connectionWithSocketForDevice
                = findUnpluggedSocket();
        if (connectionWithSocketForDevice == null) {
            throw new NoSuchElementException(
                    "Exception: there is no available sockets.");
        } else if (connectionWithSocketForDevice.getSocket()
                .getMaxPower()
                < device.getPower()) {
            throw new IllegalStateException(
                    "Exception: device's power grater"
                            + " than max socket's power");
        }
        connectionWithSocketForDevice.getSocket().setDevice(device);
        device.setPluggedIn(true);
        connectionWithSocketForDevice.setDevice(device);
    }

    /**
     * Remove device method
     * @param device device to remove
     * @return was the device removed
     */
    public boolean unplugDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException(
                    "Invalid argument: expected device,"
                            + " but have null.");
        }
        for (Connection connection : connections) {
            if (connection.getDevice() == device) {
                device.setTurnedOn(false);
                device.setPluggedIn(false);
                connection.setDevice(null);
                return true;
            }
        }

        return false;
    }

    /**
     * Remove device method
     * @return total connections power
     */
    public int calculatePower() {
        int totalPower = 0;
        for (Connection connection : connections) {
            if (connection.getDevice() != null) {
                totalPower += connection.getDevice().getPower();
            }
        }
        return totalPower;
    }

    /**
     * Sort by any device comparator
     * @param comparator device comparator
     */
    public void sort(Comparator<Device> comparator) {
        Comparator<Connection> byDevice =
                (Connection o1, Connection o2)->Comparator.nullsFirst(comparator)
                        .compare(o1.getDevice(), o2.getDevice());
        connections.sort(byDevice);
    }

    /**
     * Overloaded find method for finding devices
     * @param devices array with find results
     * @param filters device's filter(s) to find matches
     */
    public int findDevice(ArrayList<Device> devices,
                   Filter<Device>... filters) {
        boolean addFlag = true;
        for (Connection connection : connections) {
            Device currentDevice = connection.getDevice();
            if (currentDevice != null) {
                for (Filter<Device> filter : filters) {
                    if (!filter.check(currentDevice)) {
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    devices.add(currentDevice);
                }
                addFlag = true;
            }
        }
        return devices.size();
    }

    /**
     * Overloaded find method for finding sockets
     * @param sockets array with find results
     * @param filters socket's filter(s) to find matches
     */
    public int findSocket(ArrayList<Socket> sockets,
                   Filter<Socket>... filters) {
        boolean addFlag = true;
        for (Connection connection : connections) {
            Socket currentSocket = connection.getSocket();
            if (currentSocket != null) {
                for (Filter<Socket> filter : filters) {
                    if (!filter.check(currentSocket)) {
                        addFlag = false;
                        break;
                    }
                }
                if (addFlag) {
                    sockets.add(currentSocket);
                }
                addFlag = true;
            }
        }
        return sockets.size();
    }

    /**
     * Finding unplugged method
     * @return unplugged socket
     */
    public Connection findUnpluggedSocket() {
        for (Connection connection : connections) {
            Socket currSocket = connection.getSocket();
            if (currSocket.getDevice()
                    == null) {
                return connection;
            }
        }
        return null;
    }

    /**
     * Connections setter
     * @param connections to set up
     */
    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    /**
     * Connections getter
     * @return connections
     */
    public ArrayList<Connection> getConnections() {
        return this.connections;
    }

    /**
     * Max connections count getter
     * @return max connections count
     */
    public int getMaxConnectionsCount() {
        return this.maxConnectionsCount;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        ConnectionsManager connectionsManager
                = (ConnectionsManager) obj;
        if (this.maxConnectionsCount
                != connectionsManager.maxConnectionsCount) {
            return  false;
        } else if (this.connections.size()
                != connectionsManager.connections.size()) {
            return false;
        }
        for (int i = 0; i < this.connections.size(); i++) {
            if (!this.connections.get(i)
                    .equals(connectionsManager.connections.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns information of connections
     * @return description of connections
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        int connectionNumber = 1;
        for (Connection connection : connections) {
            resultStringBuilder.append("Connection №")
                    .append(Integer.toString(connectionNumber))
                    .append(":\n")
                    .append(connection.toString()).append("\n");
            connectionNumber++;
        }
        return resultStringBuilder.toString();
    }

    /**
     * Connection definition inner class
     */
    public static class Connection {

        /** Connection's device */
        private Device device;

        /** Connection's socket */
        private Socket socket;

        /**
         * Default constructor
         */
        Connection(Socket socket) {
            if(socket == null) {
                throw new IllegalArgumentException(
                        "Invalid argument: expected socket,"
                                + " but have null.");
            }
            this.socket = socket;
        }

        /**
         * Constructor with 2 arguments:
         * @param socket socket to set up
         * @param device device to set up
         */
        public Connection(Socket socket, Device device)
                throws IllegalArgumentException {
            if(device == null) {
                throw new IllegalArgumentException(
                        "Invalid argument: expected device,"
                                + " but have null.");
            } else if(socket == null) {
                throw new IllegalArgumentException(
                        "Invalid argument: expected socket,"
                                + " but have null.");
            }
            this.device = device;
            this.socket = socket;
        }

        /** Plug connection in */
        public void plugIn() {
            this.device.setPluggedIn(true);
        }

        /** Unplug connection */
        public void unplug() {
            this.device.setPluggedIn(false);
        }

        /**
         * Device setter
         * @param device device to set up
         */
        public void setDevice(Device device) {
            if (this.device != null) {
                this.device.setPluggedIn(false);
            }
            this.device = device;
        }

        /**
         * Device getter
         * @return device
         */
        public Device getDevice() {
            return this.device;
        }

        /**
         * Socket setter
         * @param socket socket to set up
         */
        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        /**
         * Socket getter
         * @return socket
         */
        public Socket getSocket() {
            return socket;
        }

        /**
         * Overrided equal method
         * @param obj to check
         * @return equality of objects
         */
        @Override
        public boolean equals(Object obj) {
            Connection connection = (Connection) obj;
            if (this.device == null
                    && connection.device == null) {
                return true;
            } else if (this.device == null
                    || connection.device == null) {
                return false;
            }
            return this.device.equals(connection.device)
                    && this.socket.equals(connection.socket);
        }

        /**
         * Returns information of a connection
         * @return description of a connection
         */
        @Override
        public String toString() {
            if (device == null && socket == null) {
                return "Empty connection.";
            }
            StringBuilder resultStringBuilder = new StringBuilder();
            resultStringBuilder.append("CONNECTION:\n");
            if (socket != null) {
                resultStringBuilder.append("SOCKET INFO:\n")
                        .append(socket.toString());
            } else {
                resultStringBuilder.append(" NO SOCKET.\n");
            }
            resultStringBuilder.append("\n");
            if (device != null) {
                resultStringBuilder.append("DEVICE INFO:\n")
                        .append(device.toString());
            } else {
                resultStringBuilder.append("NO DEVICE.");
            }
            return resultStringBuilder.toString();
        }
    }
}
