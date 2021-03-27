package com.epam.comparators.applience.device.lighting.lamp;

import com.epam.entities.applience.devices.lighting.Lamp;
import java.util.Comparator;

/**
 * Color temperature lamp comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ColorTemperatureComparator implements Comparator<Lamp> {
    @Override
    public int compare(Lamp o1, Lamp o2) {
        return o1.getColorTemperature() - o2.getColorTemperature();
    }
}
