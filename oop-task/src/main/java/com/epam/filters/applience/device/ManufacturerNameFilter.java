package com.epam.filters.applience.device;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;

/**
 * Manufacturer name filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class ManufacturerNameFilter implements Filter<Device> {

    /**
     * Manufacture name to check
     */
    private final String manufacturerName;

    /**
     * Manufacture name to check constructor
     * @param manufacturerName set manufacturer name to check
     */
    public ManufacturerNameFilter(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    };

    /**
     * Compares current device's manufacture name
     * @param device current device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getManufacturerName()
                .equals(this.manufacturerName);
    }
}
