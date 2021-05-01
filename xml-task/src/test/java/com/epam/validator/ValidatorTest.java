package com.epam.validator;

import com.epam.xml.XmlValidator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ValidatorTest {

    @Test
    public void validSchemaTest() throws IOException {
        boolean isValid = new XmlValidator(
                new File("resources" + File.separator + "tariffs.xsd"),
                new File("src"  + File.separator +  "test"
                        + File.separator +  "java" + File.separator
                        +  "com" + File.separator + "epam"
                        + File.separator + "resources"
                        + File.separator +"tariffs.xml"))
                .validate();
        Assert.assertTrue(isValid);
    }

    @Test
    public void invalidSchemaTest() throws IOException {
        boolean isValid = new XmlValidator(
                new File("resources" + File.separator + "tariffs.xsd"),
                new File("src"  + File.separator +  "test"
                        + File.separator +  "java" + File.separator
                        +  "com" + File.separator + "epam"
                        + File.separator + "resources"
                        + File.separator +"tariffsInvalid.xml"))
                .validate();
        Assert.assertFalse(isValid);
    }
}
