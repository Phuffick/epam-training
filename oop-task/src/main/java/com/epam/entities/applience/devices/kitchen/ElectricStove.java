package com.epam.entities.applience.devices.kitchen;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Electric stove definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ElectricStove extends Device {
    /** Count of burners in an electric stove */
    private int countOfBurners;

    /**
     * Constructor
     */
    public ElectricStove(int power, boolean isPluggedIn, int weight,
                         EnergyConsumptionClasses energyConsumption,
                         int noiseLevel, String manufacturerName,
                         PlugTypes forkType,
                         GregorianCalendar releaseDate,
                         int countOfBurners)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(countOfBurners < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: count of burners.");
        }

        this.countOfBurners = countOfBurners;
    }

    /**
     * Count of burners setter
     * @param countOfBurners count of burners to set
     */
    public void setCountOfBurners(int countOfBurners) {
        this.countOfBurners = countOfBurners;
    }

    /**
     * Count of burners getter
     * @return count of burners
     */
    public int getCountOfBurners() {
        return this.countOfBurners;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        ElectricStove electricStove = (ElectricStove) obj;
        return super.equals(obj)
                && this.countOfBurners == electricStove.countOfBurners;
    }

    /**
     * Returns information of a electric stove
     * @return description of a electric stove
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Coffee maker\n")
                .append(super.toString()).append("\n")
                .append(this.countOfBurners)
                .append(" count of burners\n");
        return resultStringBuilder.toString();
    }
}
