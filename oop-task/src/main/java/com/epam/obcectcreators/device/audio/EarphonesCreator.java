package com.epam.obcectcreators.device.audio;

import com.epam.entities.applience.devices.audio.Earphones;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Earphones creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class EarphonesCreator implements ObjectCreator {

    /** Earphones parameters list */
    private final ArrayList<String> earphonesParams;

    /**
     * Constructor
     * @param earphonesParams parameters list
     */
    public EarphonesCreator(ArrayList<String> earphonesParams) {
        if (!"Earphones".equals(earphonesParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating earphones but first param"
            );
        }
        this.earphonesParams = earphonesParams;
    }

    /**
     * Earphones creator
     * @return earphones
     */
    @Override
    public Earphones create() {
        if (earphonesParams.size() != 11
                || earphonesParams.get(4) == null
                || earphonesParams.get(6) == null
                || earphonesParams.get(7) == null
                || earphonesParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: earphones params arr exception.");
        }
        int power = Integer.parseInt(earphonesParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                earphonesParams.get(2));
        int weight = Integer.parseInt(earphonesParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                        earphonesParams.get(4));
        int noiseLevel = Integer.parseInt(earphonesParams.get(5));
        String manufacturerName = earphonesParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                earphonesParams.get(7));
        String[] date = earphonesParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int lowerBoundOfRange
                = Integer.parseInt(earphonesParams.get(9));
        int upperBoundOfRange
                = Integer.parseInt(earphonesParams.get(10));
        return new Earphones(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                lowerBoundOfRange, upperBoundOfRange);
    }
}
