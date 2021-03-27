package com.epam.filters.connectable;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;

/**
 * Power filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class PowerFilter implements Filter<Device> {
    /**
     * Power to check
     */
    private final double power;

    /**
     * Power to check constructor
     * @param power set weight to check
     */
    public PowerFilter(double power) {
        this.power = power;
    };

    /**
     * Compares current properties.applience.device's power
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getPower() == power;
    }
}
