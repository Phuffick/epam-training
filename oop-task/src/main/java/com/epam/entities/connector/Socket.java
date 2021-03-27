package com.epam.entities.connector;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.video.TvSet;
import com.epam.properties.applience.device.PlugTypes;

/**
 * Plug definition class
 *
 * @version    1.0.0 15 March 2021
 * @author     Belyack Maxim
 */
public class Socket implements Connector {

    /** Plug's max power value (Watt) */
    final private int MAX_POWER;

    /** Plug's current power value (Watt) */
    private int currPower;

    /** Connector's plug type */
    private PlugTypes plugType;

    /** Connected device */
    private Device device;

    /**
     * Constructor
     */
    public Socket(Device device, PlugTypes plugType,
                  int maxPower)
            throws IllegalArgumentException {
        if (maxPower < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: power value.");
        }
        this.MAX_POWER = maxPower;
        this.plugType = plugType;
        if(device != null) {
            this.setDevice(device);
        }
    }

    /**
     * Constructor with current power
     */
    public Socket(Device device, PlugTypes plugType,
                  int maxPower, int currPower)
            throws IllegalArgumentException {
        if (maxPower < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: power value.");
        } else if (currPower < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: curr power value.");
        }
        this.MAX_POWER = maxPower;
        this.plugType = plugType;
        this.currPower = currPower;
        if(device != null) {
            this.setDevice(device);
        }
    }

    /**
     * Max power value getter
     * @return max power value
     */
    @Override
    public int getMaxPower() {
        return MAX_POWER;
    }

    /**
     * Curr power setter
     * @param currentPower curr power setter
     */
    public void setCurrentPower(int currentPower){
        this.currPower = currentPower;
    };

    /**
     * Curr power getter
     * @return curr power
     */
    public int getCurrentPower() {
        return this.currPower;
    };

    /**
     * Plug type setter
     * @param plugType plug type to set up
     */
    @Override
    public void setPlugType(PlugTypes plugType) {
        this.plugType = plugType;
    }

    /**
     * Plug type getter
     * @return returns plug type
     */
    @Override
    public PlugTypes getPlugType() {
        return plugType;
    }

    /** Device setter
     * @param device to set up
     * */
    public void setDevice(Device device) {
        if (this.MAX_POWER < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: power value.");
        } else if (device != null
                && device.getPower() > this.MAX_POWER) {
            throw new IllegalArgumentException(
                    "Invalid argument:"
                            + "max power grater"
                            + "than device's power.");
        }
        this.device = device;
        this.device.setPluggedIn(true);
        this.currPower = device.getPower();
    }

    /**
     * Device getter
     * @return device
     */
    public Device getDevice() {
        return this.device;
    }

    /**
     * Turn on an device
     */
    public void turnOn() {
        this.device.setTurnedOn(true);
    };

    /**
     * Turn off an device
     */
    public void turnOff(){
        this.device.setTurnedOn(false);
    };

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Socket socket = (Socket) obj;
        boolean equationResult = false;
        if (this.device == null && socket.device == null) {
            return true;
        } else if (this.device != null && socket.device != null) {
            equationResult
                    = this.device.equals(socket.device);
        } else {
            return false;
        }
        return equationResult
                && this.MAX_POWER == socket.MAX_POWER
                && this.currPower == socket.currPower
                && this.plugType == socket.plugType;
    }

    /**
     * Returns information of a socket
     * @return description of a socket
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append(this.MAX_POWER)
                .append(" max power (Watt)\n")
                .append(this.currPower)
                .append(" curr power (Watt)\n")
                .append(this.plugType.getName())
                .append(" plug type\nwith");
        if (device != null) {
            resultStringBuilder.append(this.device.isTurnedOn()
                    ? " turned on "
                    : " turned off ")
                    .append("device");
        }
        return resultStringBuilder.toString();
    }
}
