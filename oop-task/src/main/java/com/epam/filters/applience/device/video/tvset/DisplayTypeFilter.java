package com.epam.filters.applience.device.video.tvset;

import com.epam.entities.applience.devices.video.TvSet;
import com.epam.filters.Filter;

/**
 * Display type TV set filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class DisplayTypeFilter implements Filter<TvSet> {
    /**
     * Display type to check
     */
    private final int displayType;

    /**
     * Display type to check constructor
     * @param displayType set display type
     */
    public DisplayTypeFilter(int displayType) {
        this.displayType = displayType;
    };

    /**
     * Compares current display type
     * @param tvSet current TV set
     * @return compare result
     */
    @Override
    public boolean check(TvSet tvSet) {
        return tvSet.getDisplayType().displayType() == displayType;
    }
}
