package com.epam.filters.applience.device.photo.camera;

import com.epam.entities.applience.devices.photo.Camera;
import com.epam.filters.Filter;

/**
 * Zoom camera filter class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ZoomFilter implements Filter<Camera> {
    /**
     * Zoom to check
     */
    private final int zoom;

    /**
     * Zoom to check constructor
     * @param zoom set zoom
     */
    public ZoomFilter(int zoom) {
        this.zoom = zoom;
    };

    /**
     * Compares current zoom
     * @param camera current camera
     * @return compare result
     */
    @Override
    public boolean check(Camera camera) {
        return camera.getZoom() == zoom;
    }
}
