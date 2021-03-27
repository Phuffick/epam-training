package com.epam.comparators.applience;

import com.epam.entities.applience.devices.Device;

import java.util.Comparator;

/**
 * Power comparator class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class WeightComparator implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        return Double.compare(o1.getWeight(), o2.getWeight());
    }
}
