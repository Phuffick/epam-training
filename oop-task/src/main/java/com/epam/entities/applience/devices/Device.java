package com.epam.entities.applience.devices;

import com.epam.entities.Connectable;
import com.epam.entities.applience.Appliance;
import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.lang.StringBuilder;
import java.util.GregorianCalendar;

/**
 * Device abstract definition class
 *
 * @version    1.0.0 11 March 2021
 * @author     Belyack Maxim
 */
public abstract class Device implements Connectable, Appliance {

    /** Device's power value variable in watt */
    protected int power;

    /** Plugged stat variable */
    protected boolean isPluggedIn;

    /** Turn on stat variable */
    protected boolean isTurnedOn = false;

    /** Device's total weight in grams */
    protected int weight;

    /** Energy consumption class */
    protected EnergyConsumptionClasses energyConsumption;

    /** Device's noise level */
    protected int noiseLevel;

    /** Device's manufacturer name */
    protected String manufacturerName;

    /** Device's release date */
    protected GregorianCalendar releaseDate;

    /** Device's plug type */
    protected PlugTypes plugType;

    public Device(int power, boolean isPluggedIn, int weight,
                  EnergyConsumptionClasses energyConsumptionClass,
                  int noiseLevel, String manufacturerName,
                  PlugTypes plugType,
                  GregorianCalendar releaseDate)
            throws IllegalArgumentException {
        if(power < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: power value.");
        } else if(weight < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: weight value.");
        } else if(noiseLevel < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: noise level value.");
        }

        this.power = power;
        this.isPluggedIn = isPluggedIn;
        this.weight = weight;
        this.energyConsumption = energyConsumptionClass;
        this.noiseLevel = noiseLevel;
        this.manufacturerName = manufacturerName;
        this.plugType = plugType;
        this.releaseDate = releaseDate;
    }

    /**
     * Makes device's new power value
     * @param power new value of power
     */
    @Override
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * Returns current power value
     * @return current power value
     */
    @Override
    public int getPower() {
        return power;
    }

    /**
     * Make a device plugged in or not
     * @param pluggedIn true to plug in, false to plug it out
     */
    @Override
    public void setPluggedIn(boolean pluggedIn) {
        isPluggedIn = pluggedIn;
    }

    /**
     * Shows if a device is plugged in
     * @return true if a device is plugged in, false if is not
     */
    @Override
    public boolean isPluggedIn() {
        return isPluggedIn;
    }

    /**
     * Make a device turned on or off
     * @param isTurnedOn true to plug in, false to turn it off
     */
    @Override
    public void setTurnedOn(boolean isTurnedOn) {
        this.isTurnedOn = isTurnedOn;
    }

    /**
     * Shows if the device is turned on
     * @return true if a device is turned on, false if off
     */
    @Override
    public boolean isTurnedOn() {
        return this.isTurnedOn;
    }

    /**
     * Weight setter
     * @param weight weight value to set up
     */
    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Weight getter
     * @return returns total device's weight
     */
    @Override
    public int getWeight() {
        return this.weight;
    }

    /**
     * Noise level setter
     * @param noiseLevel noise value to set up
     */
    @Override
    public void setNoiseLevel(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    /**
     * Noise level getter
     * @return returns total device's weight
     */
    @Override
    public int getNoiseLevel() {
        return this.noiseLevel;
    }

    /**
     * Energy consumption class setter
     * @param energyConsumption energy cons to set up
     */
    @Override
    public void setEnergyConsumption(
            EnergyConsumptionClasses energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    /**
     * Energy consumption class getter
     * @return returns device's consumption weight
     */
    @Override
    public EnergyConsumptionClasses getEnergyConsumption() {
        return this.energyConsumption;
    }

    /**
     * Manufacturer name setter
     * @param manufacturerName manufacturer name to set up
     */
    @Override
    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    /**
     * Manufacturer name getter
     * @return returns device's manufacturer name
     */
    @Override
    public String getManufacturerName() {
        return manufacturerName;
    }

    /**
     * Fork type setter
     * @param plugType fork type to set up
     */
    @Override
    public void setPlugType(PlugTypes plugType) {
        this.plugType = plugType;
    }

    /**
     * Fork type getter
     * @return returns device's fork type
     */
    @Override
    public PlugTypes getPlugType() {
        return plugType;
    }

    /**
     * Release date setter
     * @param releaseDate release date to set
     */
    @Override
    public void setReleaseDate(GregorianCalendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Release date getter
     * @return release date
     */
    @Override
    public GregorianCalendar getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Device device = (Device) obj;
        return this.power == device.power
                && this.isPluggedIn == device.isPluggedIn
                && this.isTurnedOn == device.isTurnedOn
                && this.energyConsumption == device.energyConsumption
                && this.manufacturerName.equals(device.manufacturerName)
                && this.weight == device.weight
                && this.noiseLevel == device.noiseLevel
                && this.releaseDate.equals(device.releaseDate)
                && this.plugType == device.plugType;
    }

    /**
     * Returns information of a device
     * @return description of a device
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append(this.power).append(" Watt\n")
                .append(this.isPluggedIn ? "is" : "isn't")
                .append(" plugged in\n")
                .append(this.weight).append(" grams\n")
                .append(this.energyConsumption.getName())
                .append(" energy consumption class\n")
                .append(this.noiseLevel)
                .append(" decibels noise\n")
                .append(this.releaseDate.getTime())
                .append(" release date\n")
                .append(this.plugType.getName())
                .append(" plug type\n")
                .append(this.manufacturerName)
                .append(" manufacturer name");
        return resultStringBuilder.toString();
    }
}