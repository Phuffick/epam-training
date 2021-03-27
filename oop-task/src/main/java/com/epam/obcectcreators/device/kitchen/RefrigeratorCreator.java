package com.epam.obcectcreators.device.kitchen;

import com.epam.entities.applience.devices.kitchen.Refrigerator;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Refrigerator creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class RefrigeratorCreator implements ObjectCreator {

    /** Refrigerator parameters list */
    private final ArrayList<String> refrigeratorParams;

    /**
     * Constructor
     * @param refrigeratorParams parameters list
     */
    public RefrigeratorCreator(
            ArrayList<String> refrigeratorParams) {
        if (!"Refrigerator".equals(refrigeratorParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating refrigerator but first param."
            );
        }
        this.refrigeratorParams = refrigeratorParams;
    }

    /**
     * Refrigerator creator
     * @return refrigerator
     */
    @Override
    public Refrigerator create() {
        if (refrigeratorParams.size() != 11
                || refrigeratorParams.get(4) == null
                || refrigeratorParams.get(6) == null
                || refrigeratorParams.get(7) == null
                || refrigeratorParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: refrigerator params arr exception.");
        }
        int power = Integer.parseInt(refrigeratorParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                refrigeratorParams.get(2));
        int weight = Integer.parseInt(refrigeratorParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                refrigeratorParams.get(4));
        int noiseLevel = Integer.parseInt(
                refrigeratorParams.get(5));
        String manufacturerName = refrigeratorParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                refrigeratorParams.get(7));
        String[] date = refrigeratorParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int countOfCameras
                = Integer.parseInt(refrigeratorParams.get(9));
        int volume
                = Integer.parseInt(refrigeratorParams.get(10));
        return new Refrigerator(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                countOfCameras, volume);
    }
}