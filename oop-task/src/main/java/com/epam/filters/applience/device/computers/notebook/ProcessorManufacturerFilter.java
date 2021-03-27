package com.epam.filters.applience.device.computers.notebook;

import com.epam.entities.applience.devices.computers.Notebook;
import com.epam.filters.Filter;

/**
 * Processor manufacturer's name notebook filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ProcessorManufacturerFilter
        implements Filter<Notebook> {
    /**
     *Processor manufacturer's name to check
     */
    private final String processorManufacturer;

    /**
     * Processor manufacturer's name to check constructor
     * @param processorManufacturer set Processor
     * manufacturer's name to check
     */
    public ProcessorManufacturerFilter(
            String processorManufacturer) {
        this.processorManufacturer = processorManufacturer;
    };

    /**
     * Compares current properties.applience.device's processor manufacturer's name
     * @param notebook current notebook
     * @return compare result
     */
    @Override
    public boolean check(Notebook notebook) {
        return notebook.getProcessorManufacturer()
                .equals(processorManufacturer);
    }
}
