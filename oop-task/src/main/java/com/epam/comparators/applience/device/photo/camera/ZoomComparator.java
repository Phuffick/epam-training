package com.epam.comparators.applience.device.photo.camera;

import com.epam.entities.applience.devices.photo.Camera;
import java.util.Comparator;

/**
 * Zoom camera comparator class
 *
 * @version    1.0.0 13 March 2021
 * @author     Belyack Maxim
 */
public class ZoomComparator implements Comparator<Camera> {
    @Override
    public int compare(Camera o1, Camera o2) {
        return o1.getZoom() - o2.getZoom();
    }
}
