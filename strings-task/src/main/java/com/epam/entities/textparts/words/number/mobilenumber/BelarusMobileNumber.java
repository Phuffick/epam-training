package com.epam.entities.textparts.words.number.mobilenumber;

import com.epam.entities.textparts.words.number.Number;

/**
 * Belarus mobile number definition class
 *
 * @version    1.0.0 05 April 2021
 * @author     Belyack Maxim
 */
public class BelarusMobileNumber extends MobileNumber {

    /** Mobile prefix number */
    private Number mobilePrefix;

    /** Carrier number */
    private Number carrier;

    /**
     * Belarus mobile number constructor
     * with belarus mobile number parts
     * @param internationalCallingCode to add
     * @param mobilePrefix to add
     * @param carrier to add
     */
    public BelarusMobileNumber(Number internationalCallingCode,
                        Number mobilePrefix, Number carrier) {
        this.setInternationalCallingCode(internationalCallingCode);
        this.setMobilePrefix(mobilePrefix);
        this.setCarrier(carrier);
    }

    /**
     * Mobile prefix getter
     * @return mobile prefix
     */
    public String getMobilePrefix() {
        return mobilePrefix.get();
    }

    /**
     * Mobile prefix setter
     * @param mobilePrefix to set up
     */
    public void setMobilePrefix(Number mobilePrefix) {
        this.mobilePrefix = mobilePrefix;
    }

    /**
     * Carrier getter
     * @return carrier
     */
    public String getCarrier() {
        return carrier.get();
    }

    /**
     * Carrier setter
     * @param carrier to set up
     */
    public void setCarrier(Number carrier) {
        this.carrier = carrier;
    }

    /**
     * Full mobile number getter
     * @return mobile number
     */
    @Override
    public String get() {
        return this.toString();
    }

    /**
     * Length getter
     * @return length
     */
    @Override
    public int getLength() {
        return 17;
    }

    /**
     * Full mobile number in string getter
     * @return mobile number
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("+")
                .append(internationalCallingCode.get())
                .append("(").append(getMobilePrefix()).append(")")
                .append(carrier.get().substring(0, 3)).append("-")
                .append(carrier.get().substring(3, 5)).append("-")
                .append(carrier.get().substring(5, 7));
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
        BelarusMobileNumber belarusMobileNumber
                = (BelarusMobileNumber) obj;
        return (internationalCallingCode != null
                && internationalCallingCode.equals(
                        belarusMobileNumber.internationalCallingCode))
                && (mobilePrefix != null && mobilePrefix.equals(
                        belarusMobileNumber.mobilePrefix))
                && (carrier != null && carrier.equals(
                        belarusMobileNumber.carrier));
    }
}
