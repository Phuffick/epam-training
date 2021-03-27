package com.epam.filters.applience.device.video.tvset;

import com.epam.entities.applience.devices.video.TvSet;
import com.epam.filters.Filter;

/**
 * Display frequency TV set filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class DisplayFrequencyFilter implements Filter<TvSet> {
    /**
     * Display frequency to check
     */
    private final int displayFrequency;

    /**
     * Display frequency to check constructor
     * @param displayFrequency set display frequency
     */
    public DisplayFrequencyFilter(int displayFrequency) {
        this.displayFrequency = displayFrequency;
    };

    /**
     * Compares current display frequency
     * @param tvSet current TV set
     * @return compare result
     */
    @Override
    public boolean check(TvSet tvSet) {
        return tvSet.getDisplayFrequency() == displayFrequency;
    }
}