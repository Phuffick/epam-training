package com.epam.filters.applience.device.lighting.lamp;

import com.epam.entities.applience.devices.lighting.Lamp;
import com.epam.filters.Filter;

/**
 * Color temperature lamp filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ColorTemperatureFilter implements Filter<Lamp> {
    /**
     * Color temperature to check
     */
    private final int colorTemperature;

    /**
     * Color temperature to check constructor
     * @param colorTemperature set color temperature
     */
    public ColorTemperatureFilter(int colorTemperature) {
        this.colorTemperature = colorTemperature;
    };

    /**
     * Compares current color temperature
     * @param lamp current lamp
     * @return compare result
     */
    @Override
    public boolean check(Lamp lamp) {
        return lamp.getColorTemperature() == colorTemperature;
    }
}
