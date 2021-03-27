package com.epam.comparators.applience.device.kitchen.refrigerator;

import com.epam.entities.applience.devices.kitchen.Refrigerator;
import java.util.Comparator;

/**
 * Volume refrigerator comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class VolumeComparator implements Comparator<Refrigerator> {
    @Override
    public int compare(Refrigerator o1, Refrigerator o2) {
        return o1.getVolume() - o2.getVolume();
    }
}
