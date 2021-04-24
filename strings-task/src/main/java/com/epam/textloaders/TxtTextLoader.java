package com.epam.textloaders;

import java.io.*;
import java.util.Scanner;

/**
 * Txt text loader definition class
 *
 * @version    1.0.0 06 April 2021
 * @author     Belyack Maxim
 */
public class TxtTextLoader implements FileTextLoader {

    /**  */
    private final Scanner reader;

    /**
     * Constructor with file
     * @param fileToRead file to read from
     */
    public TxtTextLoader(File fileToRead)
            throws FileNotFoundException {
        this.reader = new Scanner(new FileReader(fileToRead));
    }

    /**
     * Load from file method
     * @return text in string
     */
    @Override
    public String load() {
        StringBuilder textStringBuilder = new StringBuilder();
        while (reader.hasNext()) {
            textStringBuilder.append(reader.nextLine()).append(" ");
        }
        return textStringBuilder.toString();
    }
}
