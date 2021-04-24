package com.epam.entities.textparts.words.simpleword.leafs;

import com.epam.entities.textparts.words.simpleword.SimpleWordComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Punctuation definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class Punctuation implements SimpleWordComponent {

    /** Punctuation */
    private String value;

    /**
     * Constructor
     * @param punctuation to set up
     */
    public Punctuation(String punctuation) {
        this.set(punctuation);
    }

    /**
     * Punctuation getter
     * @return punctuation
     */
    @Override
    public String get() {
        return this.value;
    }

    /**
     * Punctuation composition setter
     * @param punctuation word to set up
     */
    public void set(String punctuation) {
        if (punctuation.length() != 1) {
            throw new IllegalArgumentException(
                    "Expected one symbol");
        }
        Pattern pattern = Pattern.compile(
                "[.,/?!@()_\"'{}\\[\\]:;\\-+=<>|$#%^&*№]");
        Matcher matcher = pattern.matcher(punctuation);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expected: punctuation");
        }
        this.value = punctuation;
    }

    /**
     * Punctuation length getter
     * @return punctuation length
     */
    @Override
    public int getLength() {
        return 1;
    }

    /**
     * Punctuation in string getter
     * @return punctuation in string
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
        Punctuation punctuation
                = (Punctuation) obj;
        return (this.value != null && this.value.equals(
                        punctuation.value));
    }
}
