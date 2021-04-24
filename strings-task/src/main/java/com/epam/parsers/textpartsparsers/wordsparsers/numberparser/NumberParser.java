package com.epam.parsers.textpartsparsers.wordsparsers.numberparser;

import com.epam.entities.textparts.words.number.Number;
import com.epam.entities.textparts.words.simpleword.leafs.Digit;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Number parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class NumberParser {

    /** Number pattern */
    private static final Pattern numberPattern
            = Pattern.compile("\\d");

    /**
     * Digits parser
     * @param number to parse
     * @return parsed digits array
     */
    public static Number parse(String number) {
        ArrayList<Digit> parsedNumber = new ArrayList<>();
        String[] digits = number.split("");
        Matcher matcher = null;
        for (String digit : digits) {
            matcher =  numberPattern.matcher(digit);
            if (matcher.matches()) {
                parsedNumber.add(new Digit(digit));
            } else {
                throw new IllegalArgumentException(
                        "Expected: digit, have: " + number);
            }
        }
        return new Number(parsedNumber);
    }
}
