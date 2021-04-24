package com.epam.parsers;

import com.epam.entities.Text;
import com.epam.entities.textparts.Sentence;
import com.epam.parsers.textpartsparsers.SentenceParser;

import java.util.ArrayList;

/**
 * Text parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class TextParser {

    /**
     * Parsing method
     * @param text to parse
     * @return parsed text object
     */
    public static Text parse(String text) {
        ArrayList<Sentence> parsedText = new ArrayList<>();
        String[] sentences = text.split("(?<=[?!.])\\s+");
        for (String sentence : sentences) {
            parsedText.add(SentenceParser.parse(sentence));
        }
        return new Text(parsedText);
    }
}
