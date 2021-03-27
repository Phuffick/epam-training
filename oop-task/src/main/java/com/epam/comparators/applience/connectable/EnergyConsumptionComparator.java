package com.epam.comparators.applience.connectable;

import com.epam.entities.applience.devices.Device;

import java.util.Comparator;

/**
 * Power comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class EnergyConsumptionComparator
        implements Comparator<Device> {
    @Override
    public int compare(Device o1, Device o2) {
        return o1.getEnergyConsumption().energyConsumption()
                - o2.getEnergyConsumption().energyConsumption();
    }
}
