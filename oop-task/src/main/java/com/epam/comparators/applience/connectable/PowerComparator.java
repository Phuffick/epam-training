package com.epam.comparators.applience.connectable;

import com.epam.entities.applience.devices.Device;

import java.util.Comparator;

/**
 * Power comparator class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class PowerComparator implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        return o1.getPower() - o2.getPower();
    }
}
