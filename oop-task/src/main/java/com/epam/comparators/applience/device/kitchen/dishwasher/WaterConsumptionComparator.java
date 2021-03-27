package com.epam.comparators.applience.device.kitchen.dishwasher;

import com.epam.entities.applience.devices.kitchen.Dishwasher;
import java.util.Comparator;

/**
 * Max load volume washing machine comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterConsumptionComparator
        implements Comparator<Dishwasher> {
    @Override
    public int compare(Dishwasher o1, Dishwasher o2) {
        return o1.getWaterConsumption() - o2.getWaterConsumption();
    }
}
