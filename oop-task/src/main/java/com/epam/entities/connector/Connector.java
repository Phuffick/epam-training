package com.epam.entities.connector;

import com.epam.properties.applience.device.PlugTypes;

/**
 * Connector interface
 *
 * @version    1.0.0 15 March 2021
 * @author     Belyack Maxim
 */
public interface Connector {
    /**
     * Max power getter
     * @return max power
     */
    public int getMaxPower();

    /**
     * Curr power setter
     * @param currentPower curr power setter
     */
    public void setCurrentPower(int currentPower);

    /**
     * Curr power getter
     * @return curr power
     */
    public int getCurrentPower();

    /**
     * Plug type getter
     * @return returns plug type
     */
    public PlugTypes getPlugType();

    /**
     * Plug type setter
     * @param plugType plug type to set up
     */
    public void setPlugType(PlugTypes plugType);
}
