package com.epam.filters.applience.device.kitchen.dishwasher;

import com.epam.entities.applience.devices.kitchen.Dishwasher;
import com.epam.filters.Filter;

/**
 * Water consumption dishwasher filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterConsumptionFilter implements Filter<Dishwasher> {
    /**
     * Water consumption to check
     */
    private final int waterConsumption;

    /**
     * Water consumption to check constructor
     * @param waterConsumption set water consumption
     */
    public WaterConsumptionFilter(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    };

    /**
     * Compares current water consumption
     * @param dishwasher current dishwasher
     * @return compare result
     */
    @Override
    public boolean check(Dishwasher dishwasher) {
        return dishwasher.getWaterConsumption() == waterConsumption;
    }
}
