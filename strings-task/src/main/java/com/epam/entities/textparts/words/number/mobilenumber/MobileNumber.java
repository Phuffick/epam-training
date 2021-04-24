package com.epam.entities.textparts.words.number.mobilenumber;

import com.epam.entities.textparts.words.number.Number;
import com.epam.entities.textparts.words.Word;

/**
 * Mobile number definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public abstract class MobileNumber implements Word {

    /** International calling code */
    protected Number internationalCallingCode;

    /**
     * International calling code getter
     * @return prefix
     */
    public String getInternationalCallingCode() {
        return internationalCallingCode.get();
    }

    /**
     * International calling code setter
     * @param internationalCallingCode to set up
     */
    public void setInternationalCallingCode(
            Number internationalCallingCode) {
        this.internationalCallingCode
                = internationalCallingCode;
    }
}
