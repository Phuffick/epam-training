package com.epam.parsers.textpartsparsers.wordsparsers.punctuations;

import com.epam.entities.textparts.words.punctuations.Punctuations;
import com.epam.entities.textparts.words.simpleword.leafs.Punctuation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Punctuations parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class PunctuationsParser {

    /** Punctuation pattern */
    private static final Pattern punctuationPattern
            = Pattern.compile("[.,/|?!@()_\"'{}\\[\\]:;\\-+=<>$#%^&*№]+");

    /**
     * Punctuations parser
     * @param punctuationsInString to parse
     * @return parsed punctuations array
     */
    public static Punctuations parse(String punctuationsInString) {
        ArrayList<Punctuation> parsedPunctuations = new ArrayList<>();
        String[] punctuations = punctuationsInString.split("");
        Matcher matcher = null;
        for (String punctuation : punctuations) {
            matcher =  punctuationPattern.matcher(punctuation);
            if (matcher.matches()) {
                parsedPunctuations.add(new Punctuation(punctuation));
            } else {
                throw new IllegalArgumentException(
                        "Expected: punctuations, have: "
                                + punctuationsInString);
            }
        }
        return new Punctuations(parsedPunctuations);
    }
}
