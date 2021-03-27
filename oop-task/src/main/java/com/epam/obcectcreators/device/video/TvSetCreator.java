package com.epam.obcectcreators.device.video;

import com.epam.entities.applience.devices.video.TvSet;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.DisplayType;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * TvSet creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class TvSetCreator implements ObjectCreator {

    /** TvSet parameters list */
    private final ArrayList<String> tvSetParams;

    /**
     * Constructor
     * @param tvSetParams parameters list
     */
    public TvSetCreator(
            ArrayList<String> tvSetParams) {
        if (!"TvSet".equals(tvSetParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating TV set but first param."
            );
        }
        this.tvSetParams = tvSetParams;
    }

    /**
     * TV set creator
     * @return TV set
     */
    @Override
    public TvSet create() {
        if (tvSetParams.size() != 12
                || tvSetParams.get(4) == null
                || tvSetParams.get(6) == null
                || tvSetParams.get(7) == null
                || tvSetParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: TV set params arr exception.");
        }
        int power = Integer.parseInt(tvSetParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                tvSetParams.get(2));
        int weight = Integer.parseInt(tvSetParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                tvSetParams.get(4));
        int noiseLevel = Integer.parseInt(
                tvSetParams.get(5));
        String manufacturerName = tvSetParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                tvSetParams.get(7));
        String[] date = tvSetParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int diagonalLength
                = Integer.parseInt(tvSetParams.get(9));
        int displayFrequency
                = Integer.parseInt(tvSetParams.get(10));
        DisplayType displayType
                = DisplayType.valueOf(tvSetParams.get(11));
        return new TvSet(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                displayType, diagonalLength,displayFrequency);
    }
}
