package com.epam.comparators.applience.device.beauty.hairdryer;

import com.epam.entities.applience.devices.beauty.Hairdryer;
import java.util.Comparator;

/**
 * Lcd display hairdryer comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class LcdDisplayComparator implements Comparator<Hairdryer> {
    @Override
    public int compare(Hairdryer o1, Hairdryer o2) {
        return Boolean.compare(o1.hasLcdDisplay(),
                               o2.hasLcdDisplay());
    }
}
