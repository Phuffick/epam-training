package com.epam.parsers.textpartsparsers.wordsparsers.punctuationsparser;

import com.epam.entities.textparts.words.punctuations.Punctuations;
import com.epam.parsers.textpartsparsers.wordsparsers.punctuations.PunctuationsParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PunctuationsParserTest {

    @Test
    public void symbolsParseTest() {
        String stringToParse
                = ".,/|?!@()_\"'{}[]:;-+=<>$#%^&*№";
        Punctuations punctuations
                = PunctuationsParser.parse(stringToParse);
        assertEquals(stringToParse, punctuations.toString());
    }
}
