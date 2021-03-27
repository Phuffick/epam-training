package com.epam.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Device parser definition
 *
 * @version    1.0.0 16 March 2021
 * @author     Belyack Maxim
 * */
public class DevicesParser implements Parser {

    /** File name */
    private final String fileToReadName;

    /** Constructor */
    public DevicesParser(String fileToReadName) {
        this.fileToReadName = fileToReadName;
    }

    /**
     * Device parser in string
     * @return device's params
     */
    public ArrayList<String> parseDevice(
            String deviceParamsToParse) {
        ArrayList<String> devicesParams = new ArrayList<>();
        Collections.addAll(devicesParams,
                deviceParamsToParse.split(";"));
        return devicesParams;
    }

    /**
     * Parsing method
     * @return list devices
     */
    @Override
    public ArrayList<ArrayList<String>> parse() {
        ArrayList<ArrayList<String>> parsedDevices
                = new ArrayList<>();
        try(BufferedReader bufferedReader
                    = new BufferedReader(
                            new FileReader(fileToReadName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                parsedDevices.add(parseDevice(line));
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println(
                    "FIle not found exception: file does not exist");
        }
        catch (IOException ioException) {
            System.out.println(
                    "IO exception: " + ioException.getMessage());
        }
        return parsedDevices;
    }
}
