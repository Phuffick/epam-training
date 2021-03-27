package com.epam.entities.applience.devices.lighting;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.kitchen.Microwave;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Lamp definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Lamp extends Device {
    /** Color temperature (K) */
    private int colorTemperature;

    /**
     * Constructor
     */
    public Lamp(int power, boolean isPluggedIn, int weight,
                EnergyConsumptionClasses energyConsumption,
                int noiseLevel, String manufacturerName,
                PlugTypes forkType,
                GregorianCalendar releaseDate,
                int colorTemperature)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(colorTemperature < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: color temperature.");
        }

        this.colorTemperature = colorTemperature;
    }

    /**
     * Color temperature setter
     * @param colorTemperature color temperature to set
     * to set
     */
    public void setColorTemperature(int colorTemperature) {
        this.colorTemperature = colorTemperature;
    }

    /**
     * Color temperature getter
     * @return color temperature
     */
    public int getColorTemperature() {
        return this.colorTemperature;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Lamp lamp = (Lamp) obj;
        return super.equals(obj)
                && this.colorTemperature == lamp.colorTemperature;
    }

    /**
     * Returns information of a lamp
     * @return description of a lamp
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Lamp\n")
                .append(super.toString()).append("\n")
                .append(this.colorTemperature)
                .append(" color temperature (K)\n");
        return resultStringBuilder.toString();
    }
}
