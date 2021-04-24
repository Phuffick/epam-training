package com.epam.entities.textparts.words.number;

import com.epam.entities.textparts.words.Word;
import com.epam.entities.textparts.words.simpleword.leafs.Digit;

import java.util.ArrayList;

/**
 * Number definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public class Number implements Word {

    /** Digits */
    private ArrayList<Digit> digits;

    /**
     * Number constructor
     * @param digits digits list
     */
    public Number(ArrayList<Digit> digits){
        this.digits = digits;
    }

    /**
     * Number getter
     * @return number in string
     */
    public String get() {
        return this.toString();
    }

    /**
     * Number length getter
     * @return number length
     */
    public int getLength() {
        return digits.size();
    }

    /**
     * Number in string getter
     * @return number in string
     */
    @Override
    public String toString() {
        StringBuilder numberInStringBuilder = new StringBuilder();
        for (Digit digit : digits) {
            numberInStringBuilder.append(digit.get());
        }
        return numberInStringBuilder.toString();
    }

    /**
     * Override equal method
     * @param obj to check
     * @return is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Number number = (Number) obj;
        return (digits != null && digits.equals(number.digits));
    }
}
