package com.epam.entities.applience.devices.kitchen;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Coffee maker definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Microwave extends Device {
    /** Volume (milliliters) in a microwave */
    private int volume;

    /**
     * Constructor
     */
    public Microwave(int power, boolean isPluggedIn, int weight,
                     EnergyConsumptionClasses energyConsumption,
                     int noiseLevel, String manufacturerName,
                     PlugTypes forkType,
                     GregorianCalendar releaseDate, int volume)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(volume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: volume.");
        }

        this.volume = volume;
    }

    /**
     * Volume setter
     * @param volume volume to set
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Volume getter
     * @return volume
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Microwave microwave = (Microwave) obj;
        return super.equals(obj)
                && this.volume == microwave.volume;
    }

    /**
     * Returns information of a microwave
     * @return description of a microwave
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Coffee maker\n")
                .append(super.toString()).append("\n")
                .append(this.volume)
                .append(" milliliters (volume)\n");
        return resultStringBuilder.toString();
    }
}
