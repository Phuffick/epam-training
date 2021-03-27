package com.epam.entities.applience.devices.audio;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Earphones definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Earphones extends Device {
    /** Upper bound of range (Hz) */
    private int upperBoundOfRange;

    /** Lower bound of range (Hz) */
    private int lowerBoundOfRange;

    /**
     * Constructor
     */
    public Earphones(int power, boolean isPluggedIn, int weight,
                     EnergyConsumptionClasses energyConsumption,
                     int noiseLevel, String manufacturerName,
                     PlugTypes forkType,
                     GregorianCalendar releaseDate,
                     int lowerBoundOfRange, int upperBoundOfRange)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(lowerBoundOfRange < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: lower bound of range.");
        } else if(upperBoundOfRange < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: upper bound of range.");
        } else if(upperBoundOfRange > lowerBoundOfRange) {
            throw new IllegalArgumentException(
                    "Invalid argument: lower > upper bound.");
        }

        this.lowerBoundOfRange = lowerBoundOfRange;
        this.upperBoundOfRange = upperBoundOfRange;
    }

    /**
     * Lower bound setter
     * @param lowerBoundOfRange lower bound to set
     */
    public void setLowerBoundOfRange(int lowerBoundOfRange) {
        this.lowerBoundOfRange = lowerBoundOfRange;
    }

    /**
     * Lower bound getter
     * @return upper bound
     */
    public int getLowerBoundOfRange() {
        return this.lowerBoundOfRange;
    }

    /**
     * Upper bound setter
     * @param upperBoundOfRange upper bound to set
     */
    public void setUpperBoundOfRange(int upperBoundOfRange) {
        this.upperBoundOfRange = upperBoundOfRange;
    }

    /**
     * Upper bound getter
     * @return upper bound
     */
    public int getUpperBoundOfRange() {
        return this.upperBoundOfRange;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Earphones earphones = (Earphones) obj;
        return super.equals(obj)
                && this.upperBoundOfRange == earphones.upperBoundOfRange
                && this.lowerBoundOfRange == earphones.lowerBoundOfRange;
    }

    /**
     * Returns information of an earphones
     * @return description of an earphones
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Earphones\n")
                .append(super.toString()).append("\n")
                .append(this.lowerBoundOfRange)
                .append(" lower bound range (Hz)\n")
                .append(this.upperBoundOfRange)
                .append(" upper bound range (Hz)\n");
        return resultStringBuilder.toString();
    }
}
