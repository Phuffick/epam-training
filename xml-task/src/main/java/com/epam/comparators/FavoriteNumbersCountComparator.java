package com.epam.comparators;

import com.epam.entities.Tariff;

import java.util.Comparator;

/**
 * Favorite numbers count comparator definition class
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class FavoriteNumbersCountComparator
        implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        return Integer.compare(o1.getFavoriteCellNumbers().size(),
                o2.getFavoriteCellNumbers().size());
    }
}
