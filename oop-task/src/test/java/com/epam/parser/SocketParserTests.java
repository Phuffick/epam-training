package com.epam.parser;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit test socket parser.
 *
 * @version    1.0.0 26 March 2021
 * @author     Belyack Maxim
 */
public class SocketParserTests {

    /**
     * Test socket
     * Socket;TYPE_B;200
     */
    @Test
    public void socketTest() {
        String [][] params = {
                {"Socket","TYPE_B", "200"}
        };
        ArrayList<ArrayList<String>> parsedSockets =
                new SocketsParser("src\\test\\java\\com\\epam\\testresouses\\sockets.txt")
                        .parse();
        for (int i = 0; i < params.length; i++) {
            assertArrayEquals(params[i], parsedSockets.get(i).toArray());
        }
    }
}
