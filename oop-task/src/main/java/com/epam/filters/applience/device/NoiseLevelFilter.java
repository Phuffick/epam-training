package com.epam.filters.applience.device;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;

/**
 * Noise level filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class NoiseLevelFilter implements Filter<Device> {
    /**
     * Noise level class to check
     */
    private final int noiseLevel;

    /**
     * Noise level to check constructor
     * @param noiseLevel set noise level to check
     */
    public NoiseLevelFilter(int noiseLevel) {
        this.noiseLevel = noiseLevel;
    };

    /**
     * Compares current properties.applience.device's noise level
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getNoiseLevel() == noiseLevel;
    }
}
