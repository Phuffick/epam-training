package com.epam.filters.applience.device.kitchen.refrigerator;

import com.epam.entities.applience.devices.kitchen.Refrigerator;
import com.epam.filters.Filter;

/**
 * Volume refrigerator comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class VolumeFilter implements Filter<Refrigerator> {
    /**
     *Volume to check
     */
    private final int volume;

    /**
     * Volume to check constructor
     * @param volume set volume
     */
    public VolumeFilter(int volume) {
        this.volume = volume;
    };

    /**
     * Compares current volume
     * @param refrigerator current refrigerator
     * @return compare result
     */
    @Override
    public boolean check(Refrigerator refrigerator) {
        return refrigerator.getVolume() == volume;
    }
}
