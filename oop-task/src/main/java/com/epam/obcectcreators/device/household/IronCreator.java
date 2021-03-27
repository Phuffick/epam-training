package com.epam.obcectcreators.device.household;

import com.epam.entities.applience.devices.household.Iron;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Iron creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class IronCreator implements ObjectCreator {

    /** Iron parameters list */
    private final ArrayList<String> ironParams;

    /**
     * Constructor
     * @param ironParams parameters list
     */
    public IronCreator(ArrayList<String> ironParams) {
        if (!"Iron".equals(ironParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating iron but first param."
            );
        }
        this.ironParams = ironParams;
    }

    /**
     * Iron creator
     * @return iron
     */
    @Override
    public Iron create() {
        if (ironParams.size() != 10
                || ironParams.get(4) == null
                || ironParams.get(6) == null
                || ironParams.get(7) == null
                || ironParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: iron params arr exception.");
        }
        int power = Integer.parseInt(ironParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                ironParams.get(2));
        int weight = Integer.parseInt(ironParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                ironParams.get(4));
        int noiseLevel = Integer.parseInt(ironParams.get(5));
        String manufacturerName = ironParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                ironParams.get(7));
        String[] date = ironParams.get(8).split("\\.");
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
                = Integer.parseInt(ironParams.get(9));
        return new Iron(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                waterTankVolume);
    }
}