package com.epam.parsers.textpartsparsers.wordsparsers.simplewordparser;

import com.epam.entities.textparts.words.simpleword.composite.SimpleWord;
import com.epam.entities.textparts.words.simpleword.leafs.Digit;
import com.epam.entities.textparts.words.simpleword.leafs.Letter;
import com.epam.entities.textparts.words.simpleword.leafs.Punctuation;
import org.junit.Assert;
import org.junit.Test;

public class SimpleWordParserTest {

    @Test
    public void symbolsParseTest() {
        String stringToParse
                = "my.Test32try?to";
        SimpleWord simpleWordParsed
                = SimpleWordParser.parse(stringToParse);

        SimpleWord simpleWordExpected = new SimpleWord();
        simpleWordExpected.add(new Letter("m"));
        simpleWordExpected.add(new Letter("y"));
        simpleWordExpected.add(new Punctuation("."));
        simpleWordExpected.add(new Letter("T"));
        simpleWordExpected.add(new Letter("e"));
        simpleWordExpected.add(new Letter("s"));
        simpleWordExpected.add(new Letter("t"));
        simpleWordExpected.add(new Digit("3"));
        simpleWordExpected.add(new Digit("2"));
        simpleWordExpected.add(new Letter("t"));
        simpleWordExpected.add(new Letter("r"));
        simpleWordExpected.add(new Letter("y"));
        simpleWordExpected.add(new Punctuation("?"));
        simpleWordExpected.add(new Letter("t"));
        simpleWordExpected.add(new Letter("o"));

        Assert.assertEquals(simpleWordExpected, simpleWordParsed);
    }

    private void assertEquals() {
    }
}
