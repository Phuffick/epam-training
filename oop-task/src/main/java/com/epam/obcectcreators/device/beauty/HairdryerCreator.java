package com.epam.obcectcreators.device.beauty;

import com.epam.entities.applience.devices.beauty.Hairdryer;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Hairdryer creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class HairdryerCreator implements ObjectCreator {

    /** Hairdryer parameters list */
    private final ArrayList<String> hairdryerParams;

    /**
     * Constructor
     * @param hairdryerParams parameters list
     */
    public HairdryerCreator(ArrayList<String> hairdryerParams) {
        if (!"Hairdryer".equals(hairdryerParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating hairdryer but first param"
            );
        }
        this.hairdryerParams = hairdryerParams;
    }

    /**
     * Hairdryer creator
     * @return hairdryer
     */
    @Override
    public Hairdryer create() {
        if (hairdryerParams.size() != 10
                || hairdryerParams.get(4) == null
                || hairdryerParams.get(6) == null
                || hairdryerParams.get(7) == null
                || hairdryerParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: hairdryer params arr exception.");
        }
        int power = Integer.parseInt(hairdryerParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                hairdryerParams.get(2));
        int weight = Integer.parseInt(hairdryerParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                hairdryerParams.get(4));
        int noiseLevel = Integer.parseInt(hairdryerParams.get(5));
        String manufacturerName = hairdryerParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                hairdryerParams.get(7));
        String[] date = hairdryerParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        boolean lcdDisplay
                = Boolean.parseBoolean(hairdryerParams.get(9));
        return new Hairdryer(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                lcdDisplay);
    }
}