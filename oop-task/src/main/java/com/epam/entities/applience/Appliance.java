package com.epam.entities.applience;

import java.util.GregorianCalendar;

/**
 * Appliance interface
 *
 * @version    1.0.0 11 March 2021
 * @author     Belyack Maxim
 */
public interface Appliance {
    /**
     * Weight setter
     * @param weight weight value to set up
     */
    void setWeight(int weight);

    /**
     * Weight getter
     * @return returns total appliance's weight
     */
    int getWeight();

    /**
     * Noise level setter
     * @param noiseLevel noise value to set up
     */
    public void setNoiseLevel(int noiseLevel);

    /**
     * Noise level getter
     * @return returns total properties.applience.device's weight
     */
    public int getNoiseLevel();

    /**
     * Manufacturer name setter
     * @param manufacturerName manufacturer name to set up
     */
    void setManufacturerName(String manufacturerName);

    /**
     * Manufacturer name getter
     * @return returns properties.applience.device's manufacturer name
     */
    String getManufacturerName();

    /**
     * Release date setter
     * @param releaseDate release date to set
     */
    public void setReleaseDate(GregorianCalendar releaseDate);

    /**
     * Release date getter
     * @return release date
     */
    public GregorianCalendar getReleaseDate();
}
