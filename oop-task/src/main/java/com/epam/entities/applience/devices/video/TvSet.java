package com.epam.entities.applience.devices.video;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.photo.Camera;
import com.epam.properties.applience.device.DisplayType;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Coffee maker definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class TvSet extends Device {
    /** Diagonal (centimetres) */
    private int diagonalLength;

    /** Display frequency */
    private int displayFrequency;

    /** Display type */
    private DisplayType displayType;

    /**
     * Constructor
     */
    public TvSet(int power, boolean isPluggedIn, int weight,
                 EnergyConsumptionClasses energyConsumption,
                 int noiseLevel, String manufacturerName,
                 PlugTypes forkType,
                 GregorianCalendar releaseDate,
                 DisplayType displayType, int displayFrequency,
                 int diagonalLength)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(diagonalLength < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: diagonal value.");
        } else if(displayFrequency < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: display frequency.");
        }

        this.diagonalLength = diagonalLength;
        this.displayType = displayType;
        this.displayFrequency = displayFrequency;
    }

    /**
     * Diagonal length setter
     * @param diagonalLength diagonal length to set
     */
    public void setDiagonalLength(int diagonalLength) {
        this.diagonalLength = diagonalLength;
    }

    /**
     * Diagonal length getter
     * @return diagonal length
     */
    public int getDiagonalLength() {
        return this.diagonalLength;
    }

    /**
     * Display type setter
     * @param displayType display type to set
     */
    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    /**
     * Display type getter
     * @return display type
     */
    public DisplayType getDisplayType() {
        return this.displayType;
    }

    /**
     * Display frequency setter
     * @param displayFrequency display frequency to set
     */
    public void setDisplayFrequency(int displayFrequency) {
        this.displayFrequency = displayFrequency;
    }

    /**
     * Display frequency getter
     * @return display frequency
     */
    public int getDisplayFrequency() {
        return this.displayFrequency;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        TvSet tvSet = (TvSet) obj;
        return super.equals(obj)
                && this.diagonalLength == tvSet.diagonalLength
                && this.displayFrequency == tvSet.displayFrequency
                && this.displayType == tvSet.displayType;
    }

    /**
     * Returns information of a TV set
     * @return description of a TV set
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("TV set\n")
                .append(super.toString()).append("\n")
                .append(this.displayType.getName())
                .append(" display\n")
                .append(this.diagonalLength)
                .append(" with length (centimetres)\n")
                .append(", ").append(this.displayFrequency)
                .append(" frequency (Hz)");
        return resultStringBuilder.toString();
    }
}
