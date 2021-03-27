package com.epam.entities;

import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

/**
 * Electrical appliance interface
 *
 * @version    1.0.0 11 March 2021
 * @author     Belyack Maxim
 */
public interface Connectable {
    /**
     * Makes new power value
     * @param power new value of power
     */
    public void setPower(int power);

    /**
     * Returns current power value
     * @return current power value
     */
    public int getPower();

    /**
     * Make plugged in or not
     * @param pluggedIn true to plug in, false to plug it out
     */
    public void setPluggedIn(boolean pluggedIn);

    /**
     * Shows if is plugged in
     * @return true if is plugged in, false if is not
     */
    public boolean isPluggedIn();

    /**
     * Make a device turned on or off
     * @param isTurnedOn true to plug in, false to turn it off
     */
    public void setTurnedOn(boolean isTurnedOn);

    /**
     * Shows if the device is turned on
     * @return true if is turned on, false if off
     */
    public boolean isTurnedOn();

    /**
     * Energy consumption class setter
     * @param energyConsumption energy cons to set up
     */
    public void setEnergyConsumption(
            EnergyConsumptionClasses energyConsumption);

    /**
     * Energy consumption class getter
     * @return returns consumption weight
     */
    public EnergyConsumptionClasses getEnergyConsumption();

    /**
     * Fork type getter
     * @return returns plug type
     */
    public PlugTypes getPlugType();

    /**
     * Fork type setter
     * @param plugType plug type to set up
     */
    public void setPlugType(PlugTypes plugType);

}
