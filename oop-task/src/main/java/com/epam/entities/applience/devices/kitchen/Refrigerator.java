package com.epam.entities.applience.devices.kitchen;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Refrigerator definition class
 *
 * @version    1.0.0 11 March 2021
 * @author     Belyack Maxim
 */
public class Refrigerator extends Device {
    /** Count of freezing cameras in a refrigerator */
    private int countOfCameras;

    /** Volume (milliliters)*/
    private int volume;

    /**
     * Constructor
     */
    public Refrigerator(int power, boolean isPluggedIn, int weight,
                        EnergyConsumptionClasses energyConsumption,
                        int noiseLevel, String manufacturerName,
                        PlugTypes forkType,
                        GregorianCalendar releaseDate,
                        int countOfCameras, int volume)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(countOfCameras < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: count of cameras value.");
        } else if(volume < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: volume value.");
        }

        this.countOfCameras = countOfCameras;
        this.volume = volume;
    }

    /**
     * Count of cameras setter
     * @param countOfCameras count of cameras value to set
     */
    public void setCountOfCameras(int countOfCameras) {
        this.countOfCameras = countOfCameras;
    }

    /**
     * Count of cameras getter
     * @return count of cameras
     */
    public int getCountOfCameras() {
        return this.countOfCameras;
    }

    /**
     * Volume value setter
     * @param volume volume value to set
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Volume value getter
     * @return volume value
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
        Refrigerator refrigerator = (Refrigerator) obj;
        return super.equals(obj)
                && this.volume == refrigerator.volume
                && this.countOfCameras == refrigerator.countOfCameras;
    }

    /**
     * Returns information of a refrigerator
     * @return description of a refrigerator
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Refrigerator\n")
                .append(super.toString()).append("\n")
                .append(this.countOfCameras)
                .append(" count of cameras\n")
                .append(this.volume)
                .append(" volume (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
