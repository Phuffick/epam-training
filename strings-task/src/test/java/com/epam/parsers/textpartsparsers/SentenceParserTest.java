package com.epam.parsers.textpartsparsers;

import com.epam.entities.textparts.Sentence;
import org.junit.Assert;
import org.junit.Test;

public class SentenceParserTest {

    @Test
    public void sentenceParseTest() {
        String stringToParse
                = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        Sentence sentence = SentenceParser.parse(stringToParse);
        String expected
                = "Lorem ipsum dolor sit amet , consectetur adipiscing elit .";
        Assert.assertEquals(expected, sentence.toString().trim());
    }
}
