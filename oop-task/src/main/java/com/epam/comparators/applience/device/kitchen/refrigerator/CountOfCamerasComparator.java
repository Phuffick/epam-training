package com.epam.comparators.applience.device.kitchen.refrigerator;

import com.epam.entities.applience.devices.kitchen.Refrigerator;
import java.util.Comparator;

/**
 * Count of cameras refrigerator comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class CountOfCamerasComparator
        implements Comparator<Refrigerator> {
    @Override
    public int compare(Refrigerator o1, Refrigerator o2) {
        return o1.getCountOfCameras() - o2.getCountOfCameras();
    }
}
