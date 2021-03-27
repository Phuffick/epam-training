package com.epam.entities.applience.devices.photo;

import com.epam.entities.applience.devices.Device;
import com.epam.entities.applience.devices.lighting.Lamp;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;
import java.util.GregorianCalendar;

/**
 * Camera definition class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class Camera extends Device {
    /** Zoom */
    private int zoom;

    /**
     * Constructor
     */
    public Camera(int power, boolean isPluggedIn, int weight,
                  EnergyConsumptionClasses energyConsumption,
                  int noiseLevel, String manufacturerName,
                  PlugTypes forkType,
                  GregorianCalendar releaseDate, int zoom)
            throws IllegalArgumentException {
        super(power, isPluggedIn, weight, energyConsumption,
                noiseLevel, manufacturerName, forkType, releaseDate);

        if(zoom < 0) {
            throw new IllegalArgumentException(
                    "Invalid argument: zoom.");
        }

        this.zoom = zoom;
    }

    /**
     * Zoom setter
     * @param zoom zoom to set
     * to set
     */
    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * Zoom getter
     * @return zoom
     */
    public int getZoom() {
        return this.zoom;
    }

    /**
     * Overrided equal method
     * @param obj to check
     * @return equality of objects
     */
    @Override
    public boolean equals(Object obj) {
        Camera camera = (Camera) obj;
        return super.equals(obj)
                && this.zoom == camera.zoom;
    }

    /**
     * Returns information of a camera
     * @return description of a camera
     */
    @Override
    public String toString() {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Camera\n")
                .append(super.toString()).append("\n")
                .append(this.zoom)
                .append(" zoom\n");
        return resultStringBuilder.toString();
    }
}
