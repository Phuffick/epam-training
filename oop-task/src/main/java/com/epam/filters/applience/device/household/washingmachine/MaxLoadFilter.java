package com.epam.filters.applience.device.household.washingmachine;

import com.epam.entities.applience.devices.household.WashingMachine;
import com.epam.filters.Filter;

/**
 * Max load washing machine filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class MaxLoadFilter implements Filter<WashingMachine> {
    /**
     * Max load to check
     */
    private final int maxLoad;

    /**
     * Max load to check constructor
     * @param maxLoad set max load volume
     */
    public MaxLoadFilter(int maxLoad) {
        this.maxLoad = maxLoad;
    };

    /**
     * Compares current max load
     * @param washingMachine current washing machine
     * @return compare result
     */
    @Override
    public boolean check(WashingMachine washingMachine) {
        return washingMachine.getMaxLoadVolume() == maxLoad;
    }
}
