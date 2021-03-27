package com.epam.comparators.applience.device.video.tvset;

import com.epam.entities.applience.devices.video.TvSet;
import java.util.Comparator;

/**
 * Display type TV set comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class DisplayTypeComparator implements Comparator<TvSet> {
    @Override
    public int compare(TvSet o1, TvSet o2) {
        return o1.getDisplayType().displayType()
                - o2.getDisplayType().displayType();
    }
}
