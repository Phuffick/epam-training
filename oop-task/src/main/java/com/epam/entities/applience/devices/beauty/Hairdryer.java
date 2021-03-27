package com.epam.entities.applience.devices.beauty;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Hairdryer definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Hairdryer extends Device {
    /** Has LSD display in an electrical properties.applience.device */
    private boolean lcdDisplay;

    /**
     * Constructor
     */
    public Hairdryer(int power, boolean isPluggedIn, int weight,
                     EnergyConsumptionClasses energyConsumption,
                     int noiseLevel, String manufacturerName,
                     PlugTypes forkType,
                     GregorianCalendar releaseDate,
                     boolean lcdDisplay)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        this.lcdDisplay = lcdDisplay;
    }

    /**
     * LSD display stat setter
     * @param lcdDisplayStat has of has not LSD
     */
    public void setLcdDisplayStat(boolean lcdDisplayStat) {
        this.lcdDisplay = lcdDisplayStat;
    }

    /**
     * Has LSD display or has not
     * @return has of has not LSD
     */
    public boolean hasLcdDisplay() {
        return this.lcdDisplay;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Hairdryer hairdryer = (Hairdryer) obj;
        return super.equals(obj)
                && this.lcdDisplay == hairdryer.lcdDisplay;
    }

    /**
     * Returns information of a hairdryer
     * @return description of a hairdryer
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Hairdryer\n")
                .append(super.toString()).append("\n")
                .append(this.lcdDisplay ? "Has" : "Hasn't")
                .append(" LCD display\n")
                .append(" water tank volume (milliliters)\n");
        return resultStringBuilder.toString();
    }
}
