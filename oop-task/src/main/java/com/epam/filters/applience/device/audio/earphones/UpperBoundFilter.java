package com.epam.filters.applience.device.audio.earphones;

import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.filters.Filter;

/**
 * Upper bound earphones filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class UpperBoundFilter implements Filter<Earphones> {
    /**
     * Upper bound to check
     */
    private final int upperBound;

    /**
     * Upper bound to check constructor
     * @param upperBound set upper bound
     */
    public UpperBoundFilter(int upperBound) {
        this.upperBound = upperBound;
    };

    /**
     * Compares current properties.applience.device's upper bound
     * @param earphones current earphones
     * @return compare result
     */
    @Override
    public boolean check(Earphones earphones) {
        return earphones.getUpperBoundOfRange() == upperBound;
    }
}