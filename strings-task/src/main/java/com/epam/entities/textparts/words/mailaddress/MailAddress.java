package com.epam.entities.textparts.words.mailaddress;

import com.epam.entities.textparts.words.simpleword.composite.SimpleWord;
import com.epam.entities.textparts.words.Word;
import com.epam.entities.textparts.words.simpleword.leafs.Punctuation;

/**
 * Mail address definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public class MailAddress implements Word {

    /** Mail user name */
    private SimpleWord userName;

    /** Mail domain */
    private SimpleWord domain;

    /**
     * Mail address constructor with mail address parts
     * @param userName to set up
     * @param domain to set up
     */
    public MailAddress(SimpleWord userName, SimpleWord domain) {
        this.setUserName(userName);
        this.setDomain(domain);
    }

    /**
     * User name getter
     * @return user name
     */
    public String getUserName() {
        return userName.get();
    }

    /**
     * User name setter
     * @param userName to set up
     */
    public void setUserName(SimpleWord userName) {
        this.userName = userName;
    }

    /**
     * Domain getter
     * @return domain
     */
    public String getDomain() {
        return domain.get();
    }

    /**
     * Domain setter
     * @param domain to set up
     */
    public void setDomain(SimpleWord domain) {
        this.domain = domain;
    }

    /**
     * Mail address getter
     * @return mail address
     */
    @Override
    public String get() {
        return this.toString();
    }

    /**
     * Mail address length getter
     * @return mail address length
     */
    @Override
    public int getLength() {
        return userName.getLength() + 1 + domain.getLength();
    }

    /**
     * Mail address n string getter
     * @return mail address in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userName.get()).append("@")
                .append(domain);
        return stringBuilder.toString();
    }

    /**
     * Override equal method
     * @param obj to check
     * @return is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        MailAddress mailAddress = (MailAddress) obj;
        return (userName != null && userName.equals(mailAddress.userName))
                && (domain != null && domain.equals(mailAddress.domain));
    }
}
