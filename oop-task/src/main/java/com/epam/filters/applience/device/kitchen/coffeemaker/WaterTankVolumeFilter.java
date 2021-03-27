package com.epam.filters.applience.device.kitchen.coffeemaker;

import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.filters.Filter;

/**
 * Water tank volume coffee maker filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class WaterTankVolumeFilter implements Filter<CoffeeMaker> {
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
     * @param coffeeMaker current coffee maker
     * @return compare result
     */
    @Override
    public boolean check(CoffeeMaker coffeeMaker) {
        return coffeeMaker.getWaterTankVolume() == waterTankVolume;
    }
}