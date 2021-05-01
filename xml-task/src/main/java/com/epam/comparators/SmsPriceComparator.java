package com.epam.comparators;

import com.epam.entities.Tariff;

import java.util.Comparator;

public class SmsPriceComparator implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        return o1.getSmsPrice().compareTo(o2.getSmsPrice());
    }
}
