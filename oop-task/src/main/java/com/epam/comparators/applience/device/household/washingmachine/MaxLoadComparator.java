package com.epam.comparators.applience.device.household.washingmachine;

import com.epam.entities.applience.devices.household.WashingMachine;
import java.util.Comparator;

/**
 * Max load volume washing machine comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class MaxLoadComparator
        implements Comparator<WashingMachine> {
    @Override
    public int compare(WashingMachine o1, WashingMachine o2) {
        return o1.getMaxLoadVolume() - o2.getMaxLoadVolume();
    }
}
