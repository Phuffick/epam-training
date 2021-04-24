package com.epam.entities.textparts.words;

/**
 * Word definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public interface Word {

    /**
     * Word getter
     * @return Sentence component
     */
    public String get();

    /**
     * Sentence component length getter
     * @return Sentence component length
     */
    public int getLength();
}