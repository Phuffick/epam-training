package com.epam.filters.applience.device.kitchen.microwave;

import com.epam.entities.applience.devices.kitchen.Microwave;
import com.epam.filters.Filter;

/**
 * Volume microwave comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class VolumeFilter implements Filter<Microwave> {
    /**
     * Volume to check
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
     * @param microwave current microwave
     * @return compare result
     */
    @Override
    public boolean check(Microwave microwave) {
        return microwave.getVolume() == volume;
    }
}
