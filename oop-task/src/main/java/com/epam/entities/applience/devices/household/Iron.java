package com.epam.entities.applience.devices.household;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.computers.Notebook;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Iron definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Iron extends Device {
    /** Water tank volume (milliliters) in a coffee maker */
    private int waterTankVolume;

    /**
     * Constructor
     */
    public Iron(int power, boolean isPluggedIn, int weight,
                EnergyConsumptionClasses energyConsumption,
                int noiseLevel, String manufacturerName,
                PlugTypes forkType,
                GregorianCalendar releaseDate,
                int waterTankVolume)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(waterTankVolume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: water tank volume.");
        }

        this.waterTankVolume = waterTankVolume;
    }

    /**
     * Water tank volume setter
     * @param waterTankVolume water tank volume to set
     */
    public void setWaterTankVolume(int waterTankVolume) {
        this.waterTankVolume = waterTankVolume;
    }

    /**
     * Water tank volume getter
     * @return water tank volume
     */
    public int getWaterTankVolume() {
        return this.waterTankVolume;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Iron iron = (Iron) obj;
        return super.equals(obj)
                && this.waterTankVolume == iron.waterTankVolume;
    }

    /**
     * Returns information of an iron
     * @return description of an iron
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Iron\n")
                .append(super.toString()).append("\n")
                .append(this.waterTankVolume)
                .append(" water tank volume (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
