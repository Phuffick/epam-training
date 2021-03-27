package com.epam.filters.connectable;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;

/**
 * Weight filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class PluggedPropertyFilter implements Filter<Device> {
    /**
     * Plugging stat to check
     */
    private final boolean isPlugged;

    /**
     * Plugging stat to check constructor
     * @param isPlugged set weight to check
     */
    public PluggedPropertyFilter(boolean isPlugged) {
        this.isPlugged = isPlugged;
    };

    /**
     * Compares current properties.applience.device's plug stat
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.isPluggedIn() == isPlugged;
    }
}
