package com.epam.parsers.textpartsparsers.wordsparsers.mailaddressparser;

import static org.junit.Assert.*;

import com.epam.entities.textparts.words.mailaddress.MailAddress;
import org.junit.Test;

public class MailAddressTest {

    @Test
    public void firstTypeParseTest() {
        String stringToParse
                = "peter.parker@zylker.com";
        MailAddress address = MailAddressParser.parse(stringToParse);
        assertEquals(stringToParse, address.toString());
    }

    @Test
    public void secondTypeParseTest() {
        String stringToParse
                = "peter.p@zylker.com";
        MailAddress address = MailAddressParser.parse(stringToParse);
        assertEquals(stringToParse, address.toString());
    }

    @Test
    public void thirdTypeParseTest() {
        String stringToParse
                = "peter@zylker.com";
        MailAddress address = MailAddressParser.parse(stringToParse);
        assertEquals(stringToParse, address.toString());
    }

    @Test
    public void fourthTypeParseTest() {
        String stringToParse
                = "john.e.smith@company.com";
        MailAddress address = MailAddressParser.parse(stringToParse);
        assertEquals(stringToParse, address.toString());
    }

    @Test
    public void fifthTypeParseTest() {
        String stringToParse
                = "j_smith@company.com";
        MailAddress address = MailAddressParser.parse(stringToParse);
        assertEquals(stringToParse, address.toString());
    }
}
