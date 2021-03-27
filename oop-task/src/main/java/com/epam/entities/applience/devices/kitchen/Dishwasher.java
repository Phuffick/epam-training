package com.epam.entities.applience.devices.kitchen;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Dishwasher definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Dishwasher extends Device {

    /** Water tank volume (in milliliters) in a coffee maker */
    private int waterConsumption;

    /**
     * Constructor
     */
    public Dishwasher(int power, boolean isPluggedIn, int weight,
                      EnergyConsumptionClasses energyConsumption,
                      int noiseLevel, String manufacturerName,
                      PlugTypes forkType,
                      GregorianCalendar releaseDate,
                      int waterConsumption)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(waterConsumption < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: water consumption volume.");
        }

        this.waterConsumption = waterConsumption;
    }

    /**
     * Water consumption volume setter
     * @param waterConsumption water consumption volume to set
     */
    public void setWaterConsumption(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    /**
     * Water consumption volume getter
     * @return water consumption volume
     */
    public int getWaterConsumption() {
        return this.waterConsumption;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Dishwasher dishwasher = (Dishwasher) obj;
        return super.equals(obj)
                && this.waterConsumption == dishwasher.waterConsumption;
    }

    /**
     * Returns information of a dishwasher
     * @return description of a dishwasher
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Dishwasher\n")
                .append(super.toString()).append("\n")
                .append(this.waterConsumption)
                .append(" water consumption (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
