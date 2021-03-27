package com.epam.filters.applience.device.beauty.hairdryer;

import com.epam.entities.applience.devices.beauty.Hairdryer;
import com.epam.filters.Filter;

/**
 * LCD display stat filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class LcdDisplayFilter
        implements Filter<Hairdryer> {
    /**
     * LCD display stat to check
     */
    private final boolean hasLcdDisplay;

    /**
     * LCD display stat to check constructor
     * @param hasLcdDisplay set LCD display stat
     */
    public LcdDisplayFilter(boolean hasLcdDisplay) {
        this.hasLcdDisplay = hasLcdDisplay;
    };

    /**
     * Compares current properties.applience.device's LCD properties.applience.device stat
     * @param hairdryer current hairdryer
     * @return compare result
     */
    @Override
    public boolean check(Hairdryer hairdryer) {
        return hairdryer.hasLcdDisplay() == hasLcdDisplay;
    }
}