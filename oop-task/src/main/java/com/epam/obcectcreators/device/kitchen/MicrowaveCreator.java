package com.epam.obcectcreators.device.kitchen;

import com.epam.entities.applience.devices.kitchen.Microwave;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Microwave creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class MicrowaveCreator implements ObjectCreator {

    /** Microwave parameters list */
    private final ArrayList<String> microwaveParams;

    /**
     * Constructor
     * @param microwaveParams parameters list
     */
    public MicrowaveCreator(
            ArrayList<String> microwaveParams) {
        if (!"Microwave".equals(microwaveParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating microwave but first param."
            );
        }
        this.microwaveParams = microwaveParams;
    }

    /**
     * Microwave creator
     * @return microwave
     */
    @Override
    public Microwave create() {
        if (microwaveParams.size() != 10
                || microwaveParams.get(4) == null
                || microwaveParams.get(6) == null
                || microwaveParams.get(7) == null
                || microwaveParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: microwave params arr exception.");
        }
        int power = Integer.parseInt(microwaveParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                microwaveParams.get(2));
        int weight = Integer.parseInt(microwaveParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                microwaveParams.get(4));
        int noiseLevel = Integer.parseInt(
                microwaveParams.get(5));
        String manufacturerName = microwaveParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                microwaveParams.get(7));
        String[] date = microwaveParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int volume
                = Integer.parseInt(microwaveParams.get(9));
        return new Microwave(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                volume);
    }
}