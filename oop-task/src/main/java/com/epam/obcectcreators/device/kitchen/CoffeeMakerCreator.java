package com.epam.obcectcreators.device.kitchen;

import com.epam.entities.applience.devices.kitchen.CoffeeMaker;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Coffee maker creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class CoffeeMakerCreator implements ObjectCreator {

    /** Coffee maker parameters list */
    private final ArrayList<String> coffeeMakerParams;

    /**
     * Constructor
     * @param coffeeMakerParams parameters list
     */
    public CoffeeMakerCreator(
            ArrayList<String> coffeeMakerParams) {
        if (!"CoffeeMaker".equals(coffeeMakerParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating coffee maker but first param."
            );
        }
        this.coffeeMakerParams = coffeeMakerParams;
    }

    /**
     * Coffee maker creator
     * @return coffee maker
     */
    @Override
    public CoffeeMaker create() {
        if (coffeeMakerParams.size() != 11
                || coffeeMakerParams.get(4) == null
                || coffeeMakerParams.get(6) == null
                || coffeeMakerParams.get(7) == null
                || coffeeMakerParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: coffee maker params arr exception.");
        }
        int power = Integer.parseInt(coffeeMakerParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                coffeeMakerParams.get(2));
        int weight = Integer.parseInt(coffeeMakerParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                coffeeMakerParams.get(4));
        int noiseLevel = Integer.parseInt(
                coffeeMakerParams.get(5));
        String manufacturerName = coffeeMakerParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                coffeeMakerParams.get(7));
        String[] date = coffeeMakerParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int waterTankVolume
                = Integer.parseInt(coffeeMakerParams.get(9));
        int coffeeTankVolume
                = Integer.parseInt(coffeeMakerParams.get(10));
        return new CoffeeMaker(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                waterTankVolume, coffeeTankVolume);
    }
}