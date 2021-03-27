package com.epam.filters.connectable;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;
import com.epam.properties.applience.device.PlugTypes;

/**
 * Fork type properties.applience.device filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ForkTypeFilter implements Filter<Device> {
    /**
     * Fork type to check
     */
    private final PlugTypes forkType;

    /**
     * Fork type to check constructor
     * @param forkType set fork type to check
     */
    public ForkTypeFilter(PlugTypes forkType) {
        this.forkType = forkType;
    };

    /**
     * Compares current properties.applience.device's fork type
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getPlugType() == this.forkType;
    }
}
