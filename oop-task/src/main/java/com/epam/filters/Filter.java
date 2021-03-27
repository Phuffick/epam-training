package com.epam.filters;

import java.util.Comparator;

/**
 * Filter interface
 *
 * @version    1.0.0 12 March 2021
 * @author     Belyack Maxim
 */
public interface Filter<T> {
    /**
     * Compares current properties.
     * @param obj current properties
     * @return compare result
     */
    boolean check(T obj);
}
