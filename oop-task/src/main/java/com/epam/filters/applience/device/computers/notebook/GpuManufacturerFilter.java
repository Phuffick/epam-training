package com.epam.filters.applience.device.computers.notebook;

import com.epam.entities.applience.devices.computers.Notebook;
import com.epam.filters.Filter;

/**
 * GPU manufacturer's name notebook filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class GpuManufacturerFilter implements Filter<Notebook> {
    /**
     * GPU manufacturer's name to check
     */
    private final String gpuManufacturer;

    /**
     * GPU manufacturer's name to check constructor
     * @param gpuManufacturer set GPU manufacturer's name to check
     */
    public GpuManufacturerFilter(String gpuManufacturer) {
        this.gpuManufacturer = gpuManufacturer;
    };

    /**
     * Compares current properties.applience.device's GPU manufacturer's name
     * @param notebook current GPU manufacturer's name
     * @return compare result
     */
    @Override
    public boolean check(Notebook notebook) {
        return notebook.getGpuManufacturer().equals(gpuManufacturer);
    }
}
