package com.epam.comparators.applience.connectable;

import com.epam.entities.applience.devices.Device;

import java.util.Comparator;

/**
 * Power comparator class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class PlugStatComparator implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        return Boolean.compare(o1.isPluggedIn(), o2.isPluggedIn());
    }
}
