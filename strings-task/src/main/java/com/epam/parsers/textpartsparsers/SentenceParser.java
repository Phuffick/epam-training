package com.epam.parsers.textpartsparsers;

import com.epam.entities.textparts.Sentence;
import com.epam.entities.textparts.words.Word;
import com.epam.entities.textparts.words.punctuations.Punctuations;
import com.epam.parsers.textpartsparsers.wordsparsers.mailaddressparser.MailAddressParser;
import com.epam.parsers.textpartsparsers.wordsparsers.numberparser.BelarusMobileNumberParser;
import com.epam.parsers.textpartsparsers.wordsparsers.numberparser.NumberParser;
import com.epam.parsers.textpartsparsers.wordsparsers.punctuations.PunctuationsParser;
import com.epam.parsers.textpartsparsers.wordsparsers.simplewordparser.SimpleWordParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sentence parser definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class SentenceParser {

    /** Punctuations pattern */
    private static final Pattern punctuationsPattern
            = Pattern.compile(
                    "[.,/|?!@()_\"'{}\\[\\]:;\\-+=<>$#%^&*№]+");

    /** Belarus telephone number pattern */
    private static final Pattern belarusTelephoneNumberPattern
            = Pattern.compile(
                    "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}");

    /** Mail address parser pattern */
    private static final Pattern mailAddressParserPattern
            = Pattern.compile(
                    "([\\w]+\\.)*([\\w-_]+)@([\\w-_]+)*(\\.[a-z]{2,6})");

    /** Simple word pattern */
    private static final Pattern simpleWordPattern
            = Pattern.compile("[a-zA-Zа-яА-Я\\p{P}\\d]+");

    /** Numbers pattern */
    private static final Pattern numbersPattern
            = Pattern.compile("\\d+");

    /**
     * Parsing method
     * @param sentence to parse
     * @return sentence
     */
    public static Sentence parse(String sentence) {
        boolean hadMatches = false;
        ArrayList<Word> parsedSentence
                = new ArrayList<>();
        String[] words = sentence.split(" ");
        Matcher matcher;
        Punctuations punctuationsInTheEnd = null;
        for (String word : words ) {
            String[] punctuationsInWord = word.split("[^\\p{P}]");
            if (punctuationsInWord.length != 0) {
                String separatedPunctuations
                        = punctuationsInWord[
                                punctuationsInWord.length - 1];
                matcher = punctuationsPattern.matcher(
                        separatedPunctuations);
                if (matcher.matches()) {
                    punctuationsInTheEnd = PunctuationsParser.parse(
                            separatedPunctuations);
                    word = word.substring(0, word.length()
                            - punctuationsInTheEnd.getLength());
                }
            }
            matcher = belarusTelephoneNumberPattern.matcher(word);
            if (matcher.matches()) {
                parsedSentence.add(
                        BelarusMobileNumberParser.parse(word));
                hadMatches = true;
            }
            matcher.usePattern(numbersPattern);
            if (matcher.matches()) {
                parsedSentence.add(NumberParser.parse(word));
                hadMatches = true;
            }
            matcher.usePattern(mailAddressParserPattern);
            if (matcher.matches()) {
                parsedSentence.add(MailAddressParser.parse(word));
                hadMatches = true;
            }
            matcher.usePattern(simpleWordPattern);
            if (matcher.matches() && !hadMatches) {
                parsedSentence.add(SimpleWordParser.parse(word));
            }

            if (punctuationsInTheEnd != null) {
                parsedSentence.add(punctuationsInTheEnd);
                punctuationsInTheEnd = null;
            }
            hadMatches = false;
        }
        return new Sentence(parsedSentence);
    }
}
