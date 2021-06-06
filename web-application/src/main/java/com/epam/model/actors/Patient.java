package com.epam.model.actors;

import com.epam.model.Entity;

import java.util.*;

/**
 * Patient definition class.
 * Containing name.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Patient extends Entity {

    /** Patient name */
    private String name;

    /**
     * Default constructor
     */
    public Patient() {}

    /**
     * Name getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     * @param name to set up
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are two patient equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(name, patient.name);
    }

    /**
     * Override hash code method
     * @return patient hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Override to string method
     * @return patient in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient{id=").append(getId())
                .append(", name=").append(name);
        stringBuilder.append("medical cards: ");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
