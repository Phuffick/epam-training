package com.epam.textloaders;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TxtTextLoaderTest {

    @Test
    public void txtTextLoaderTest() throws IOException {
        TxtTextLoader txtTextLoader = new TxtTextLoader(new File(
                "src" + File.separator + "test" + File.separator
                        + "java" + File.separator + "com" + File.separator
                        + "epam" + File.separator + "resources"
                        + File.separator + "test-1-text.txt"));
        String loadedText = txtTextLoader.load();
        String expected = "Good +375(29)623-32-11. Chapter 1.  INTRODUCTION TO OOP AND CLASSES.  "
                + "Every fool can write a program that computer can understand. "
                + "Good programmer writes a code that human can understand.  "
                + "Martin Fowler (example.post.intext@mail.net). "
                + "(+375(29)623-32-14 example of telephone number).  Basic concepts of OOP.";
        Assert.assertEquals(expected, loadedText.trim());
    }
}
