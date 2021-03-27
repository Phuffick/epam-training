package com.epam.obcectcreators.device.computers;

import com.epam.entities.applience.devices.computers.Notebook;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Notebook creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class NotebookCreator implements ObjectCreator {

    /** Notebook parameters list */
    private final ArrayList<String> notebookParams;

    /**
     * Constructor
     * @param notebookParams parameters list
     */
    public NotebookCreator(ArrayList<String> notebookParams) {
        if (!"Notebook".equals(notebookParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating notebook but first param"
            );
        }
        this.notebookParams = notebookParams;
    }

    /**
     * Notebook creator
     * @return notebook
     */
    @Override
    public Notebook create() {
        if (notebookParams.size() != 11
                || notebookParams.get(4) == null
                || notebookParams.get(6) == null
                || notebookParams.get(7) == null
                || notebookParams.get(8) == null) {
            throw  new IllegalArgumentException(
                    "Exception: notebook params arr exception.");
        }
        int power = Integer.parseInt(notebookParams.get(1));
        boolean isPluggedIn = Boolean.parseBoolean(
                notebookParams.get(2));
        int weight = Integer.parseInt(notebookParams.get(3));
        EnergyConsumptionClasses energyConsumptionClasses
                = EnergyConsumptionClasses.valueOf(
                notebookParams.get(4));
        int noiseLevel = Integer.parseInt(notebookParams.get(5));
        String manufacturerName = notebookParams.get(6);
        PlugTypes plugTypes = PlugTypes.valueOf(
                notebookParams.get(7));
        String[] date = notebookParams.get(8).split("\\.");
        if (date.length != 3) {
            throw  new IllegalArgumentException(
                    "Exception: wrong date.");
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(
                Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2])
        );
        String processorManufacturer = notebookParams.get(9);
        String gpuManufacturer = notebookParams.get(10);
        return new Notebook(power, isPluggedIn, weight,
                energyConsumptionClasses, noiseLevel,
                manufacturerName, plugTypes, gregorianCalendar,
                processorManufacturer, gpuManufacturer);
    }
}