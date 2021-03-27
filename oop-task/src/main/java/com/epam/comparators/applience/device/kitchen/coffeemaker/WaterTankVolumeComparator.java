package com.epam.comparators.applience.device.kitchen.coffeemaker;

import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import java.util.Comparator;

/**
 * Water tank volume coffee maker comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterTankVolumeComparator
        implements Comparator<CoffeeMaker> {
    @Override
    public int compare(CoffeeMaker o1, CoffeeMaker o2) {
        return o1.getWaterTankVolume() - o2.getWaterTankVolume();
    }
}
