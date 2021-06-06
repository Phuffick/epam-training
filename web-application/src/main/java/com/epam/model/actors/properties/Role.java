package com.epam.model.actors.properties;

/**
 * Role enum.
 * Roles list that will have different right.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public enum Role {
    ADMINISTRATOR("Admin"),
    DOCTOR("Doctor"),
    NURSE("Nurse");

    /** Role name */
    private String roleName;

    /**
     * Role default constructor
     * @param roleName to set up
     */
    private Role(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Role id getter
     * @return role id
     */
    public Long getId() {
        return (long) ordinal();
    }

    /**
     * Role name getter
     * @return role name
     */
    public String getName() {
        return roleName;
    }

    /**
     * Role name setter
     * @param roleName to set up
     */
    public void setName(String roleName) {
        this.roleName = roleName;
    }
}
