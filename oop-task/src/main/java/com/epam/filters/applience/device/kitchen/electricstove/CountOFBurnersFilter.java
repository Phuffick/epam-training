package com.epam.filters.applience.device.kitchen.electricstove;

import com.epam.entities.applience.devices.kitchen.ElectricStove;
import com.epam.filters.Filter;

/**
 * Count of burners electric stove comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class CountOFBurnersFilter implements Filter<ElectricStove> {
    /**
     * Count of burners to check
     */
    private final int countOfBurners;

    /**
     * Count of burners to check constructor
     * @param countOfBurners set count of burners
     */
    public CountOFBurnersFilter(int countOfBurners) {
        this.countOfBurners = countOfBurners;
    };

    /**
     * Compares current count of burners
     * @param electricStove current electric stove
     * @return compare result
     */
    @Override
    public boolean check(ElectricStove electricStove) {
        return electricStove.getCountOfBurners() == countOfBurners;
    }
}
