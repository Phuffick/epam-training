package com.epam.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Socket parser definition
 *
 * @version    1.0.0 16 March 2021
 * @author     Belyack Maxim
 */
public class SocketsParser implements Parser {

    /** File name */
    private final String fileToReadName;

    /** Constructor */
    public SocketsParser(String fileToReadName) {
        this.fileToReadName = fileToReadName;
    }

    /**
     * Socket parser in string
     * @return device's params
     */
    public ArrayList<String> parseSocket(
            String deviceParamsToParse) {
        ArrayList<String> socketParams = new ArrayList<>();
        Collections.addAll(socketParams,
                deviceParamsToParse.split(";"));
        return socketParams;
    }

    /**
     * Parsing method
     * @return list sockets
     */
    @Override
    public ArrayList<ArrayList<String>> parse() {
        ArrayList<ArrayList<String>> parsedSocket
                = new ArrayList<>();
        try(BufferedReader bufferedReader
                    = new BufferedReader(
                new FileReader(fileToReadName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                parsedSocket.add(parseSocket(line));
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
        return parsedSocket;
    }
}
