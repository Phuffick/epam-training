package com.epam.entities.textparts.words.simpleword.leafs;

import com.epam.entities.textparts.words.simpleword.SimpleWordComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Letters definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public class Letter implements SimpleWordComponent {

    /** Letter */
    private String value;

    /**
     * Constructor
     * @param lettersWord to set up
     */
    public Letter(String lettersWord) {
        this.set(lettersWord);
    }

    /**
     * Letter getter
     * @return letter
     */
    @Override
    public String get() {
        return this.value;
    }

    /**
     * Letter setter
     * @param letter to set up
     */
    public void set(String letter) {
        if (letter.length() != 1) {
            throw new IllegalArgumentException(
                    "Expected one symbol");
        }
        Pattern pattern = Pattern.compile("[a-zA-ZА-Яа-я]");
        Matcher matcher = pattern.matcher(letter);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Expected: letter");
        }
        this.value = letter;
    }

    /**
     * Letters word length getter
     * @return letters word length
     */
    @Override
    public int getLength() {
        return value.length();
    }

    /**
     * Letter in string getter
     * @return letter in string
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
        Letter letters
                = (Letter) obj;
        return (value != null
                && value.equals(letters.value));
    }
}
