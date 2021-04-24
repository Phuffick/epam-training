package com.epam.parsers.textpartsparsers.wordsparsers.numberparser;

import com.epam.entities.textparts.words.number.Number;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberParserTest {

    @Test
    public void oneDigitNumberParseTest() {
        String digitInString;
        for (int i = 0; i < 10; i++) {
            digitInString = Integer.toString(i);
            Number nullNumber = NumberParser.parse(digitInString);
            assertEquals(digitInString, nullNumber.toString());
        }
    }

    @Test
    public void twoDigitsNumberParseTest() {
        String digitInString;
        for (int i = 10; i < 100; i++) {
            digitInString = Integer.toString(i);
            Number nullNumber = NumberParser.parse(digitInString);
            assertEquals(digitInString, nullNumber.toString());
        }
    }

    @Test
    public void threeDigitsNumberParseTest() {
        String digitInString;
        for (int i = 100; i < 1000; i++) {
            digitInString = Integer.toString(i);
            Number nullNumber = NumberParser.parse(digitInString);
            assertEquals(digitInString, nullNumber.toString());
        }
    }
}
