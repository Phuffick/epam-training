package com.epam.comparators;

import com.epam.entities.Tariff;

import java.util.Comparator;

/**
 * Out network calls price comparator definition class
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class OutNetworkCallsPriceComparator
        implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        return o1.getOutNetworkCallsPrice()
                .compareTo(o2.getOutNetworkCallsPrice());
    }
}
