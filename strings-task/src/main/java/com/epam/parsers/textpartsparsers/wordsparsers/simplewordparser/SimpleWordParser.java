package com.epam.parsers.textpartsparsers.wordsparsers.simplewordparser;

import com.epam.entities.textparts.words.simpleword.SimpleWordComponent;
import com.epam.entities.textparts.words.simpleword.composite.SimpleWord;
import com.epam.entities.textparts.words.simpleword.leafs.Digit;
import com.epam.entities.textparts.words.simpleword.leafs.Letter;
import com.epam.entities.textparts.words.simpleword.leafs.Punctuation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple word parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class SimpleWordParser {

    /** Letter pattern */
    private static final Pattern letterPattern
            = Pattern.compile("[a-zA-Zа-яА-Я]");

    /** Number pattern */
    private static final Pattern numberPattern
            = Pattern.compile("\\d");

    /** Punctuation pattern */
    private static final Pattern punctuationPattern
            = Pattern.compile("[.,/|?!@()_\"'{}\\[\\]:;\\-+=<>$#%^&*№]");

    /**
     * Parsing method
     * @param simpleWord to parse
     * @return simple word
     */
    public static SimpleWord parse(
            String simpleWord) {
        ArrayList<SimpleWordComponent> parsedSimpleWord
                = new ArrayList<>();
        String[] simpleWordComponents
                = simpleWord.split("");
        Matcher matcher;
        for (String simpleWordComponent : simpleWordComponents) {
            matcher = letterPattern
                    .matcher(simpleWordComponent);
            if (matcher.matches()) {
                parsedSimpleWord.add(
                        new Letter(simpleWordComponent));
            }
            matcher.usePattern(numberPattern);
            if (matcher.matches()) {
                parsedSimpleWord.add(
                        new Digit(simpleWordComponent));
            }
            matcher.usePattern(punctuationPattern);
            if (matcher.matches()) {
                parsedSimpleWord.add(
                        new Punctuation(simpleWordComponent));
            }
        }
        return new SimpleWord(parsedSimpleWord);
    }
}
