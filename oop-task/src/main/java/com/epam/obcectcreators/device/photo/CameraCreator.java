package com.epam.obcectcreators.device.photo;

import com.epam.entities.applience.devices.lighting.Lamp;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Camera creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class CameraCreator implements ObjectCreator {

    /** Lamp parameters list */
    private final ArrayList<String> cameraParams;

    /**
     * Constructor
     * @param cameraParams parameters list
     */
    public CameraCreator(
            ArrayList<String> cameraParams) {
        if (!"Camera".equals(cameraParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating camera but first param."
            );
        }
        this.cameraParams = cameraParams;
    }

    /**
     * Camera creator
     * @return camera
     */
    @Override
    public Lamp create() {
        if (cameraParams.size() != 10
                || cameraParams.get(4) == null
                || cameraParams.get(6) == null
                || cameraParams.get(7) == null
                || cameraParams.get(8) == null) {
            throw  new IllegalArgumentException(

                    "Exception: lamp params arr exception.");
        }
        int power = Integer.parseInt(cameraParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                cameraParams.get(2));
        int weight = Integer.parseInt(cameraParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                cameraParams.get(4));
        int noiseLevel = Integer.parseInt(
                cameraParams.get(5));
        String manufacturerName = cameraParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                cameraParams.get(7));
        String[] date = cameraParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        int zoom
                = Integer.parseInt(cameraParams.get(9));
        return new Lamp(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                zoom);
    }
}