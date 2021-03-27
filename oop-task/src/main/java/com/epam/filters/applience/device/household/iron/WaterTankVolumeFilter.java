package com.epam.filters.applience.device.household.iron;

import com.epam.entities.applience.devices.household.Iron;
import com.epam.filters.Filter;

/**
 * Water tank iron filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterTankVolumeFilter implements Filter<Iron> {
    /**
     * Water tank volume to check
     */
    private final int waterTankVolume;

    /**
     * Water tank volume to check constructor
     * @param waterTankVolume set water tank volume
     */
    public WaterTankVolumeFilter(int waterTankVolume) {
        this.waterTankVolume = waterTankVolume;
    };

    /**
     * Compares current water tank volume
     * @param iron current iron
     * @return compare result
     */
    @Override
    public boolean check(Iron iron) {
        return iron.getWaterTankVolume() == waterTankVolume;
    }
}
