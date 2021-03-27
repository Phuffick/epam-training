package com.epam.comparators.applience.device.household.iron;

import com.epam.entities.applience.devices.household.Iron;
import java.util.Comparator;

/**
 * Water tank iron comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterTankVolumeComparator implements Comparator<Iron> {
    @Override
    public int compare(Iron o1, Iron o2) {
        return o1.getWaterTankVolume() - o2.getWaterTankVolume();
    }
}
