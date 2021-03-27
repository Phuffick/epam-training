package com.epam.obcectcreators.device;

import com.epam.entities.applience.devices.Device;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.obcectcreators.device.audio.EarphonesCreator;
import com.epam.obcectcreators.device.beauty.HairdryerCreator;
import com.epam.obcectcreators.device.computers.NotebookCreator;
import com.epam.obcectcreators.device.household.IronCreator;
import com.epam.obcectcreators.device.household.WashingMachineCreator;
import com.epam.obcectcreators.device.kitchen.*;
import com.epam.obcectcreators.device.lighting.LampCreator;
import com.epam.obcectcreators.device.photo.CameraCreator;
import com.epam.obcectcreators.device.video.TvSetCreator;

import java.util.ArrayList;

/**
 * Plug definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class DeviceCreator implements ObjectCreator {

    /** Device parameters list */
    private final ArrayList<String> deviceParams;

    /**
     * Constructor
     * @param deviceParams parameters list
     */
    public DeviceCreator(ArrayList<String> deviceParams)
            throws IndexOutOfBoundsException {
        if (deviceParams.size() == 0) {
            throw  new IndexOutOfBoundsException(
                    "Exception: socket params arr is empty.");
        }
        this.deviceParams = deviceParams;
    }

    /**
     * Device creator
     * @return device
     */
    @Override
    public Device create() {
        String deviceName = deviceParams.get(0);
        if ("Earphones".equals(deviceName)) {
            return new EarphonesCreator(deviceParams).create();
        } else if ("Hairdryer".equals(deviceName)) {
            return new HairdryerCreator(deviceParams).create();
        } else if ("Notebook".equals(deviceName)) {
            return new NotebookCreator(deviceParams).create();
        } else if ("Iron".equals(deviceName)) {
            return new IronCreator(deviceParams).create();
        } else if ("WashingMachine".equals(deviceName)) {
            return new WashingMachineCreator(deviceParams).create();
        } else if ("CoffeeMaker".equals(deviceName)) {
            return new CoffeeMakerCreator(deviceParams).create();
        } else if ("Dishwasher".equals(deviceName)) {
            return new DishwasherCreator(deviceParams).create();
        } else if ("ElectricStove".equals(deviceName)) {
            return new ElectricStoveCreator(deviceParams).create();
        } else if ("Microwave".equals(deviceName)) {
            return new MicrowaveCreator(deviceParams).create();
        } else if ("Refrigerator".equals(deviceName)) {
            return new RefrigeratorCreator(deviceParams).create();
        } else if ("Lamp".equals(deviceName)) {
            return new LampCreator(deviceParams).create();
        } else if ("Camera".equals(deviceName)) {
            return new CameraCreator(deviceParams).create();
        } else if ("TvSet".equals(deviceName)) {
            return new TvSetCreator(deviceParams).create();
        }
        throw  new IllegalArgumentException(
                "Exception: connector name is unknown.");
    }
}
