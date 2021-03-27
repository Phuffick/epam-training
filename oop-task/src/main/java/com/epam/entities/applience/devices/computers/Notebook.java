package com.epam.entities.applience.devices.computers;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Notebook definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Notebook extends Device {
    /** Processor manufacturer's mane */
    private String processorManufacturer;

    /** GPU manufacturer's mane */
    private String gpuManufacturer;

    /**
     * Constructor
     */
    public Notebook(int power, boolean isPluggedIn, int weight,
                    EnergyConsumptionClasses energyConsumption,
                    int noiseLevel, String manufacturerName,
                    PlugTypes forkType,
                    GregorianCalendar releaseDate,
                    String processorManufacturer,
                    String gpuManufacturer)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        this.processorManufacturer = processorManufacturer;
        this.gpuManufacturer = gpuManufacturer;
    }

    /**
     * Processor manufacturer's name setter
     * @param processorManufacturer processor manufacturer's name
     * to set
     */
    public void setProcessorManufacturer(
            String processorManufacturer) {
        this.processorManufacturer = processorManufacturer;
    }

    /**
     * Processor manufacturer's name getter
     * @return processor manufacturer's name
     */
    public String getProcessorManufacturer() {
        return this.processorManufacturer;
    }

    /**
     * GPU manufacturer's name setter
     * @param gpuManufacturer GPU manufacturer's name to set
     */
    public void setGpuManufacturer(String gpuManufacturer) {
        this.gpuManufacturer = gpuManufacturer;
    }

    /**
     * GPU manufacturer's name getter
     * @return GPU manufacturer's name
     */
    public String getGpuManufacturer() {
        return this.gpuManufacturer;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Notebook notebook = (Notebook) obj;
        return super.equals(obj)
                && this.gpuManufacturer.equals(notebook.gpuManufacturer)
                && this.processorManufacturer.equals(
                        notebook.processorManufacturer);
    }

    /**
     * Returns information of a notebook
     * @return description of a notebook
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Notebook\n")
                .append(super.toString()).append("\n")
                .append(this.processorManufacturer)
                .append(" processor manufacturer's name\n")
                .append(this.gpuManufacturer)
                .append(" GPU manufacturer's name\n");
        return resultStringBuilder.toString();
    }
}
