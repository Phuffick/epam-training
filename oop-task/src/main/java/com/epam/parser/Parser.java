package com.epam.parser;

import java.util.ArrayList;

/**
 * Parser interface definition
 *
 * @version    1.0.0 16 March 2021
 * @author     Belyack Maxim
 * */
public interface Parser {

    /**
     * Parsing method
     * @return list devices
     */
    public ArrayList<ArrayList<String>> parse();
}
