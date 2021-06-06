package com.epam.controller;

/**
 * Direct definition class.
 * Contains direct parts
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Direct {

    /** Direct url */
    private final String url;

    /** Direction type */
    private final DirectionType directionType;

    /**
     * Constructor with 2 params
     * @param url of direction
     * @param directionType type of direction
     */
    public Direct(String url, DirectionType directionType) {
        this.url = url;
        this.directionType = directionType;
    }

    /**
     * Constructor with 1 params (default direction type - REDIRECT)
     * @param url of direction
     */
    public Direct(String url) {
        this(url, DirectionType.REDIRECT);
    }

    /**
     * Url getter
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Direction type getter
     * @return direction type
     */
    public DirectionType getDirectionType() {
        return directionType;
    }
}
