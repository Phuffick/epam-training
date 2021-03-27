package com.epam.comparators.applience.device.kitchen.electricstove;

import com.epam.entities.applience.devices.kitchen.ElectricStove;
import java.util.Comparator;

/**
 * Count of burners electric stove comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class CountOFBurnersComparator
        implements Comparator<ElectricStove> {
    @Override
    public int compare(ElectricStove o1, ElectricStove o2) {
        return o1.getCountOfBurners() - o2.getCountOfBurners();
    }
}
