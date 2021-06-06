package com.epam.model.actors;

import com.epam.model.Entity;
import com.epam.model.actors.properties.Role;

import java.util.Objects;

/**
 * User definition class.
 * Defines user, containing login, password and user's role
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class User extends Entity {

    /** User's login */
    private String login;

    /** User's password */
    private String password;

    /** User's role */
    private Role role;

    /**
     * Default constructor
     */
    public User() {}

    /**
     * Login getter
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Login setter
     * @param login to set up
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Password getter
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password to set up
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Role getter
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Role setter
     * @param role to set up
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && role == user.role;
    }

    /**
     * Override hash code method
     * @return user hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(login, password, role);
    }

    /**
     * Override to string method
     * @return user in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User{id=").append(getId())
                .append(", login=").append(login)
                .append(", role=").append(role).append('}');
        return stringBuilder.toString();
    }
}
