package com.epam.parsers;

import com.epam.entities.Text;
import org.junit.Assert;
import org.junit.Test;

public class TextParserTest {

    @Test
    public void textParseTest() {
        String stringToParse
                = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        Text sentence = TextParser.parse(stringToParse);
        String expected
                = "Lorem ipsum dolor sit amet , consectetur adipiscing elit . Lorem ipsum dolor sit amet , consectetur adipiscing elit .";
        Assert.assertEquals(expected, sentence.toString().trim());
    }
}
