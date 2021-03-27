package com.epam.obcectcreators.socket;

import com.epam.entities.connector.Socket;
import com.epam.obcectcreators.ObjectCreator;
import com.epam.properties.applience.device.PlugTypes;

import java.util.ArrayList;

/**
 * Socket creator definition class
 *
 * @version    1.0.0 19 March 2021
 * @author     Belyack Maxim
 */
public class SocketCreator implements ObjectCreator {

    /** Socket parameters list */
    private final ArrayList<String> socketParams;

    /**
     * Constructor
     * @param socketParams parameters list
     */
    public SocketCreator(ArrayList<String> socketParams) {
        if (!"Socket".equals(socketParams.get(0))) {
            throw new IllegalArgumentException(
                    "Illegal argument: creating socket but first param."
            );
        }
        this.socketParams = socketParams;
    }

    /**
     * Socket creator
     * @return socket
     */
    @Override
    public Socket create() {
        if (socketParams.size() != 3
                || socketParams.get(1) == null
                || socketParams.get(2) == null) {
            throw  new IllegalArgumentException(
                    "Exception: socket params arr exception.");
        }
        PlugTypes plugType = PlugTypes.valueOf(socketParams.get(1));
        int maxPower = Integer.parseInt(socketParams.get(2));
        return new Socket(null, plugType, maxPower);
    }
}
