package com.epam.parsers.textpartsparsers.wordsparsers.numberparser;

import com.epam.entities.textparts.words.number.mobilenumber.BelarusMobileNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BelarusMobileNumberTest {

    @Test
    public void firstTypeParseTest() {
        String stringToParse
                = "+375(29)984-24-12";
        BelarusMobileNumber belarusMobileNumber
                = BelarusMobileNumberParser.parse(stringToParse);
        assertEquals(stringToParse, belarusMobileNumber.toString());
    }
}
