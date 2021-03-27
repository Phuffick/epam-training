package com.epam.filters.connectable;

import com.epam.entities.applience.devices.Device;
import com.epam.properties.applience.device.EnergyConsumptionClasses;
import com.epam.filters.Filter;

/**
 * Energy consumption class filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class ConsumptionFilter implements Filter<Device> {
    /**
     * Energy consumption class to check
     */
    private final EnergyConsumptionClasses energyConsumptionClass;

    /**
     * Energy consumption class to check constructor
     * @param energyConsumptionClass set weight to check
     */
    public ConsumptionFilter(
            EnergyConsumptionClasses energyConsumptionClass) {
        this.energyConsumptionClass = energyConsumptionClass;
    };

    /**
     * Compares current properties.applience.device's energy consumption class
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getEnergyConsumption()
                == energyConsumptionClass;
    }
}
