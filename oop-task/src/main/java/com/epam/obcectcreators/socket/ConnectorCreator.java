package com.epam.obcectcreators.socket;

import com.epam.entities.connector.Connector;
import com.epam.obcectcreators.ObjectCreator;

import java.util.ArrayList;

/**
 * Connector creator definition class
 *
 * @version    1.0.0 15 March 2021
 * @author     Belyack Maxim
 */
public class ConnectorCreator implements ObjectCreator {

    /** Connector parameters list */
    private final ArrayList<String> connectorParams;

    /**
     * Constructor
     * @param socketParams parameters list to set up
     */
    public ConnectorCreator(ArrayList<String> socketParams)
            throws IndexOutOfBoundsException {
        if (socketParams.size() == 0) {
            throw  new IllegalArgumentException(
                    "Exception: socket params arr is empty.");
        }
        this.connectorParams = socketParams;
    }

    /**
     * Connector creator
     * @return connector
     */
    @Override
    public Connector create() {
        String connectorName = connectorParams.get(0);
        if ("Socket".equals(connectorName)) {
            return new SocketCreator(connectorParams).create();
        }
        throw  new IllegalArgumentException(
                "Exception: connector name is unknown.");
    }
}
