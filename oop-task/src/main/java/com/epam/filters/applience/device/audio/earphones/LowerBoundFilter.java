package com.epam.filters.applience.device.audio.earphones;

import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.filters.Filter;

/**
 * Lower bound earphones filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class LowerBoundFilter implements Filter<Earphones> {
    /**
     * Lower bound to check
     */
    private final int lowerBound;

    /**
     * Lower bound to check constructor
     * @param lowerBound set lower bound
     */
    public LowerBoundFilter(int lowerBound) {
        this.lowerBound = lowerBound;
    };

    /**
     * Compares current properties.applience.device's lower bound
     * @param earphones current earphones
     * @return compare result
     */
    @Override
    public boolean check(Earphones earphones) {
        return earphones.getLowerBoundOfRange() == lowerBound;
    }
}
