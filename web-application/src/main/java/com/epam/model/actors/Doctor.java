package com.epam.model.actors;

import com.epam.model.Entity;

import java.util.Objects;

/**
 * Doctor definition class.
 * Containing doctor's name.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Doctor extends Entity {

    /** Doctor's name */
    private String name;

    /**
     * Default constructor
     */
    public Doctor() {}

    /**
     * Doctor's name getter
     * @return doctor's name
     */
    public String getName() {
        return name;
    }

    /**
     * Doctor' name setter
     * @param name to set up
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are two doctors equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name);
    }

    /**
     * Override hash code method
     * @return doctor hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Override to string method
     * @return doctor in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Doctor{id=").append(getId())
                .append(", name=").append(name).append("'}");
        return stringBuilder.toString();
    }
}
