package com.epam.comparators.applience.device.kitchen.microwave;

import com.epam.entities.applience.devices.kitchen.Microwave;
import java.util.Comparator;

/**
 * Volume microwave comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class VolumeComparator implements Comparator<Microwave> {
    @Override
    public int compare(Microwave o1, Microwave o2) {
        return o1.getVolume() - o2.getVolume();
    }
}
