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
public class CoffeeMaker extends Device {

    /** Water tank volume (milliliters) */
    private int waterTankVolume;

    /** Coffee tank volume (milliliters) r*/
    private int coffeeTankVolume;

    /**
     * Constructor
     */
    public CoffeeMaker(int power, boolean isPluggedIn, int weight,
                       EnergyConsumptionClasses energyConsumption,
                       int noiseLevel, String manufacturerName,
                       PlugTypes plugType,
                       GregorianCalendar releaseDate,
                       int waterTankVolume, int coffeeTankVolume)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, plugType, releaseDate);

        if(waterTankVolume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: water tank volume.");
        } else if(coffeeTankVolume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: coffee tank volume.");
        }

        this.waterTankVolume = waterTankVolume;
        this.coffeeTankVolume = coffeeTankVolume;
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
     * Coffee tank volume setter
     * @param coffeeTankVolume coffee tank volume to set
     */
    public void setCoffeeTankVolume(int coffeeTankVolume) {
        this.coffeeTankVolume = coffeeTankVolume;
    }

    /**
     * Coffee tank volume getter
     * @return coffee tank volume
     */
    public int getCoffeeTankVolume() {
        return this.coffeeTankVolume;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        CoffeeMaker coffeeMaker = (CoffeeMaker) obj;
        return super.equals(obj)
                && this.coffeeTankVolume == coffeeMaker.coffeeTankVolume
                && this.waterTankVolume == coffeeMaker.waterTankVolume;
    }

    /**
     * Returns information of a coffee maker
     * @return description of a coffee maker
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Coffee maker\n")
                .append(super.toString()).append("\n")
                .append(this.waterTankVolume)
                .append(" water tank volume (milliliters)\n")
                .append(this.coffeeTankVolume)
                .append(" coffee tank volume (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
