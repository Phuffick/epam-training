package com.epam.entities.applience.devices.household;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Washing machine definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WashingMachine extends Device {
    /** Max load volume (milliliters) */
    private int maxLoadVolume;

    /**
     * Constructor
     */
    public WashingMachine(int power, boolean isPluggedIn, int weight,
                          EnergyConsumptionClasses energyConsumption,
                          int noiseLevel, String manufacturerName,
                          PlugTypes forkType,
                          GregorianCalendar releaseDate,
                          int maxLoadVolume)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(maxLoadVolume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: max load volume.");
        }

        this.maxLoadVolume = maxLoadVolume;
    }

    /**
     * Max load volume setter
     * @param maxLoadVolume max load volume to set
     */
    public void setMaxLoadVolume(int maxLoadVolume) {
        this.maxLoadVolume = maxLoadVolume;
    }

    /**
     * Max load volume getter
     * @return max load volume
     */
    public int getMaxLoadVolume() {
        return this.maxLoadVolume;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        WashingMachine washingMachine = (WashingMachine) obj;
        return super.equals(obj)
                && this.maxLoadVolume == washingMachine.maxLoadVolume;
    }

    /**
     * Returns information of a washing machine
     * @return description of a washing machine
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Coffee maker\n")
                .append(super.toString()).append("\n")
                .append(this.maxLoadVolume)
                .append(" max load volume (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
