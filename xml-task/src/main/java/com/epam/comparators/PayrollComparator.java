package com.epam.comparators;

import com.epam.entities.Tariff;

import java.util.Comparator;

/**
 * Payroll comparator definition class
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class PayrollComparator implements Comparator<Tariff> {
    @Override
    public int compare(Tariff o1, Tariff o2) {
        return o1.getMonthPayroll().compareTo(o2.getMonthPayroll());
    }
}
