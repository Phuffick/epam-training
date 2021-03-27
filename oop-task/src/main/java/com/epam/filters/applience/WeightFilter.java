package com.epam.filters.applience;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;

/**
 * Weight filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class WeightFilter implements Filter<Device> {
    /**
     * Weight to check
     */
    private final double weight;

    /**
     * Weight to check constructor
     * @param weight set weight to check
     */
    public WeightFilter(double weight) {
        this.weight = weight;
    }

    /**
     * Compares current properties.applience.device's weight
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device){
        return device.getWeight() == weight;
    }
}
