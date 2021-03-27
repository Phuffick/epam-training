package com.epam.filters.applience.device.kitchen.coffeemaker;

import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.filters.Filter;

/**
 * Coffee tank volume coffee maker filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class CoffeeTankVolumeFilter implements Filter<CoffeeMaker> {
    /**
     * Coffee tank volume to check
     */
    private final int coffeeTankVolume;

    /**
     * Coffee tank volume to check constructor
     * @param coffeeTankVolume set coffee tank volume
     */
    public CoffeeTankVolumeFilter(int coffeeTankVolume) {
        this.coffeeTankVolume = coffeeTankVolume;
    };

    /**
     * Compares current coffee tank volume
     * @param coffeeMaker current coffee maker
     * @return compare result
     */
    @Override
    public boolean check(CoffeeMaker coffeeMaker) {
        return coffeeMaker.getCoffeeTankVolume() == coffeeTankVolume;
    }
}
