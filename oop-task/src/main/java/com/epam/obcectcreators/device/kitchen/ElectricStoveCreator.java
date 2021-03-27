package com.epam.obcectcreators.device.kitchen;

import com.epam.entities.applience.devices.kitchen.ElectricStove;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Electric stove creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class ElectricStoveCreator implements ObjectCreator {

    /** Electric stove parameters list */
    private final ArrayList<String> electricStoveParams;

    /**
     * Constructor
     * @param electricStoveParams parameters list
     */
    public ElectricStoveCreator(
            ArrayList<String> electricStoveParams) {
        if (!"ElectricStove".equals(electricStoveParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating electric stove but first param."
            );
        }
        this.electricStoveParams = electricStoveParams;
    }

    /**
     * Electric stove creator
     * @return electric stove
     */
    @Override
    public ElectricStove create() {
        if (electricStoveParams.size() != 10
                || electricStoveParams.get(4) == null
                || electricStoveParams.get(6) == null
                || electricStoveParams.get(7) == null
                || electricStoveParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: electric stove params arr exception.");
        }
        int power = Integer.parseInt(electricStoveParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                electricStoveParams.get(2));
        int weight = Integer.parseInt(electricStoveParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                electricStoveParams.get(4));
        int noiseLevel = Integer.parseInt(
                electricStoveParams.get(5));
        String manufacturerName = electricStoveParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                electricStoveParams.get(7));
        String[] date = electricStoveParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int countOfBurners
                = Integer.parseInt(electricStoveParams.get(9));
        return new ElectricStove(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                countOfBurners);
    }
}