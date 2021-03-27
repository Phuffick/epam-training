package com.epam.filters.applience.device.kitchen.refrigerator;

import com.epam.entities.applience.devices.kitchen.Refrigerator;
import com.epam.filters.Filter;

/**
 * Count of cameras refrigerator comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class CountOfCamerasFilter implements Filter<Refrigerator> {
    /**
     * Count of cameras to check
     */
    private final int countOfCameras;

    /**
     * Count of cameras to check constructor
     * @param countOfCameras set count of cameras
     */
    public CountOfCamerasFilter(int countOfCameras) {
        this.countOfCameras = countOfCameras;
    };

    /**
     * Compares current count of cameras
     * @param refrigerator current refrigerator
     * @return compare result
     */
    @Override
    public boolean check(Refrigerator refrigerator) {
        return refrigerator.getCountOfCameras() == countOfCameras;
    }
}
