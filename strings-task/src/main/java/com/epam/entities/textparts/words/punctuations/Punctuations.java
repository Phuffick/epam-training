package com.epam.entities.textparts.words.punctuations;

import com.epam.entities.textparts.words.Word;
import com.epam.entities.textparts.words.simpleword.leafs.Punctuation;

import java.util.ArrayList;

/**
 * Punctuation definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class Punctuations implements Word {

    /** Punctuations */
    private ArrayList<Punctuation> punctuations;

    /**
     * Punctuations constructor
     * @param punctuations punctuations list
     */
    public Punctuations(ArrayList<Punctuation> punctuations) {
        this.punctuations = punctuations;
    }

    /**
     * Punctuations getter
     * @return punctuations in string
     */
    public String get() {
        return this.toString();
    }

    /**
     * Punctuations length getter
     * @return punctuations length
     */
    public int getLength() {
        return punctuations.size();
    }

    /**
     * Punctuations in string getter
     * @return punctuations in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Punctuation punctuation : punctuations) {
            stringBuilder.append(punctuation.toString());
        }
        return stringBuilder.toString();
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
        Punctuations punctuationsObj = (Punctuations) obj;
        return (punctuations != null
                && punctuations.equals(punctuationsObj.punctuations));
    }
}
