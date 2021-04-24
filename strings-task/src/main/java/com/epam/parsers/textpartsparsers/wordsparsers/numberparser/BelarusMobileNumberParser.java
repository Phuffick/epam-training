package com.epam.parsers.textpartsparsers.wordsparsers.numberparser;

import com.epam.entities.textparts.words.number.Number;
import com.epam.entities.textparts.words.number.mobilenumber.BelarusMobileNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Belarus mobile number parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class BelarusMobileNumberParser {

    /**
     * Belarus mobile number parser
     * @param belarusMobileNumber to parse
     * @return belarus mobile number
     */
    public static BelarusMobileNumber parse(
            String belarusMobileNumber) {
        Pattern pattern = Pattern.compile(
                "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(belarusMobileNumber);
        if (!matcher.find()) {
            throw new IllegalArgumentException(
                    "Belarus cell number format doesn't match");
        }
        String[] numbers = belarusMobileNumber.split("[()-]");
        Number internationalCallingCode
                = NumberParser.parse("375");
        Number mobilePrefix = NumberParser.parse(numbers[1]);
        Number carrier = NumberParser.parse(numbers[2] + numbers[3]
                + numbers[4]);
        return new BelarusMobileNumber(internationalCallingCode,
                mobilePrefix, carrier);
    }
}
