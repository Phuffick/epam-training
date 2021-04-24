package com.epam.parsers.textpartsparsers.wordsparsers.mailaddressparser;

import com.epam.entities.textparts.words.mailaddress.MailAddress;
import com.epam.entities.textparts.words.simpleword.composite.SimpleWord;
import com.epam.parsers.textpartsparsers.wordsparsers.simplewordparser.SimpleWordParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mail address parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class MailAddressParser {

    /**
     * Mail address parser
     * @param mailAddress to parse
     * @return mailAddress
     */
    public static MailAddress parse(String mailAddress) {
        Pattern pattern = Pattern.compile(
                "([\\w]+\\.)*([\\w-_]+)@([\\w-_]+)*(\\.[a-z]{2,6})");
        Matcher matcher = pattern.matcher(mailAddress);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "Mail address format doesn't match");
        }
        String[] numbers = mailAddress.split("@");
        SimpleWord userName = SimpleWordParser.parse(numbers[0]);
        SimpleWord domain = SimpleWordParser.parse(numbers[1]);
        return new MailAddress(userName, domain);
    }
}
