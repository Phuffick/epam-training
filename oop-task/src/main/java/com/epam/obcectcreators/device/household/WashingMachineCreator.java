package com.epam.obcectcreators.device.household;

import com.epam.entities.applience.devices.household.WashingMachine;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Washing machine creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class WashingMachineCreator implements ObjectCreator {

    /** Washing machine parameters list */
    private final ArrayList<String> washingMachineParams;

    /**
     * Constructor
     * @param washingMachineParams parameters list
     */
    public WashingMachineCreator(
            ArrayList<String> washingMachineParams) {
        if (!"WashingMachine".equals(washingMachineParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating Washing machine but first param."
            );
        }
        this.washingMachineParams = washingMachineParams;
    }

    /**
     * Washing machine creator
     * @return washing machine
     */
    @Override
    public WashingMachine create() {
        if (washingMachineParams.size() != 10
                || washingMachineParams.get(4) == null
                || washingMachineParams.get(6) == null
                || washingMachineParams.get(7) == null
                || washingMachineParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: washing machine params arr exception.");
        }
        int power = Integer.parseInt(washingMachineParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                washingMachineParams.get(2));
        int weight = Integer.parseInt(washingMachineParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                washingMachineParams.get(4));
        int noiseLevel = Integer.parseInt(
                washingMachineParams.get(5));
        String manufacturerName = washingMachineParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                washingMachineParams.get(7));
        String[] date = washingMachineParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int maxLoadVolume
                = Integer.parseInt(washingMachineParams.get(9));
        return new WashingMachine(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                maxLoadVolume);
    }
}