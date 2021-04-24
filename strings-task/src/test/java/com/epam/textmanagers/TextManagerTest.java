package com.epam.textmanagers;

import com.epam.TextManager;
import com.epam.entities.textparts.words.Word;
import com.epam.parsers.textpartsparsers.wordsparsers.numberparser.BelarusMobileNumberParser;
import com.epam.textloaders.TxtTextLoader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TextManagerTest {

    @Test
    public void textManagerTest() throws FileNotFoundException {
        TextManager textManager = new TextManager(new TxtTextLoader(
                new File("src" + File.separator
                        + "test" + File.separator
                        + "java" + File.separator
                        + "com" + File.separator
                        + "epam" + File.separator + "resources"
                        + File.separator + "test-1-text.txt")));
        ArrayList<Word> res
                =  textManager.findUniqueWordInSentenceAtText(0);
        ArrayList<Word> expected = new ArrayList<>();
        expected.add(BelarusMobileNumberParser
                .parse("+375(29)623-32-11"));
        Assert.assertArrayEquals(expected.toArray(), res.toArray());
    }
}
