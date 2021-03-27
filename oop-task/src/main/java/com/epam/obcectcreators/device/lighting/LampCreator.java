package com.epam.obcectcreators.device.lighting;

import com.epam.entities.applience.devices.lighting.Lamp;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Lamp creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class LampCreator implements ObjectCreator {

    /** Lamp parameters list */
    private final ArrayList<String> lampParams;

    /**
     * Constructor
     * @param lampParams parameters list
     */
    public LampCreator(
            ArrayList<String> lampParams) {
        if (!"Lamp".equals(lampParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating lamp but first param."
            );
        }
        this.lampParams = lampParams;
    }

    /**
     * Lamp creator
     * @return lamp
     */
    @Override
    public Lamp create() {
        if (lampParams.size() != 10
                || lampParams.get(4) == null
                || lampParams.get(6) == null
                || lampParams.get(7) == null
                || lampParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: lamp params arr exception.");
        }
        int power = Integer.parseInt(lampParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                lampParams.get(2));
        int weight = Integer.parseInt(lampParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                lampParams.get(4));
        int noiseLevel = Integer.parseInt(
                lampParams.get(5));
        String manufacturerName = lampParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                lampParams.get(7));
        String[] date = lampParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int colorTemperature
                = Integer.parseInt(lampParams.get(9));
        return new Lamp(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                colorTemperature);
    }
}