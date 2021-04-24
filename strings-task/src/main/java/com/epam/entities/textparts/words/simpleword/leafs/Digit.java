package com.epam.entities.textparts.words.simpleword.leafs;

import com.epam.entities.textparts.words.simpleword.SimpleWordComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Digit definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class Digit implements SimpleWordComponent {

    /** Digit */
    private String value;

    /**
     * Constructor
     * @param digit to set up
     */
    public Digit(String digit) {
        this.set(digit);
    }

    /**
     * Digit getter
     * @return digit
     */
    @Override
    public String get() {
        return this.value;
    }

    /**
     * Digit setter
     * @param digit to set up
     */
    public void set(String digit) {
        if (digit.length() != 1) {
            throw new IllegalArgumentException(
                    "Expected one symbol.");
        }
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(digit);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expected: digit");
        }
        this.value = digit;
    }

    /**
     * Digit length getter
     * @return digit length
     */
    @Override
    public int getLength() {
        return 1;
    }

    /**
     * Digit in string getter
     * @return digit in string
     */
    @Override
    public String toString() {
        return value;
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
        Digit digit = (Digit) obj;
        return (this.value != null && this.value.equals(digit.value));
    }
}
