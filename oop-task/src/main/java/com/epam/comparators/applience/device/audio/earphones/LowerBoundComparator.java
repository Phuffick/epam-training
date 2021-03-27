package com.epam.comparators.applience.device.audio.earphones;

import com.epam.entities.applience.devices.audio.Earphones;
import java.util.Comparator;

/**
 * Lower bound earphones comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class LowerBoundComparator implements Comparator<Earphones> {
    @Override
    public int compare(Earphones o1, Earphones o2) {
        return o1.getLowerBoundOfRange() - o2.getLowerBoundOfRange();
    }
}
