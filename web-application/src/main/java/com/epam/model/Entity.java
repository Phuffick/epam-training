package com.epam.model;

import java.io.Serializable;

/**
 * Entity definition class.
 * Is an a base for other entities.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public abstract class Entity implements Serializable {

    /** Entity's id */
    private Long id;

    /**
     * Id getter
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id to set up
     */
    public void setId(Long id) {
        this.id = id;
    }
}
