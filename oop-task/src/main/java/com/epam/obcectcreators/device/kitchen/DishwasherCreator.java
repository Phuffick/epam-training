package com.epam.obcectcreators.device.kitchen;

import com.epam.entities.applience.devices.kitchen.Dishwasher;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Dishwasher creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class DishwasherCreator implements ObjectCreator {

    /** Dishwasher parameters list */
    private final ArrayList<String> dishwasherParams;

    /**
     * Constructor
     * @param dishwasherParams parameters list
     */
    public DishwasherCreator(ArrayList<String> dishwasherParams) {
        if (!"Dishwasher".equals(dishwasherParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating dishwasher but first param."
            );
        }
        this.dishwasherParams = dishwasherParams;
    }

    /**
     * Dishwasher creator
     * @return dishwasher
     */
    @Override
    public Dishwasher create() {
        if (dishwasherParams.size() != 10
                || dishwasherParams.get(4) == null
                || dishwasherParams.get(6) == null
                || dishwasherParams.get(7) == null
                || dishwasherParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: dishwasher params arr exception.");
        }
        int power = Integer.parseInt(dishwasherParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                dishwasherParams.get(2));
        int weight = Integer.parseInt(dishwasherParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                dishwasherParams.get(4));
        int noiseLevel = Integer.parseInt(
                dishwasherParams.get(5));
        String manufacturerName = dishwasherParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                dishwasherParams.get(7));
        String[] date = dishwasherParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int waterConsumption
                = Integer.parseInt(dishwasherParams.get(9));
        return new Dishwasher(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                waterConsumption);
    }
}
