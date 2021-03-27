package com.epam.filters.applience.device;

import com.epam.entities.applience.devices.Device;
import com.epam.filters.Filter;
import java.util.GregorianCalendar;

/**
 * Release date filter class
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public class ReleaseDateComparator implements Filter<Device> {
    /**
     * Release date
     */
    private final GregorianCalendar date;

    /**
     * Date to check constructor
     * @param date set date to check
     */
    public ReleaseDateComparator(GregorianCalendar date) {
        this.date = date;
    };

    /**
     * Compares current properties.applience.device's date
     * @param device current properties.applience.device
     * @return compare result
     */
    @Override
    public boolean check(Device device) {
        return device.getReleaseDate().equals(date);
    }
}
