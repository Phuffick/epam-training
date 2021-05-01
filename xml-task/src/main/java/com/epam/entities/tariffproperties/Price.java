package com.epam.entities.tariffproperties;

import java.math.BigDecimal;

/**
 * Price definition class
 *
 * @version    1.0.0 28 March 2021
 * @author     Belyack Maxim
 */
public class Price implements Comparable<Price> {

    /** Price value */
    private BigDecimal price;

    /**
     * Default constructor
     */
    public Price() {};

    /**
     * Constructor with price value
     * @param price to set up
     */
    public Price(BigDecimal price) {
        setPrice(price);
    }

    /**
     * Price setter
     * @param price to set up
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Price getter
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     *
     * @param price to parse
     * @return price class obj
     * @throws NumberFormatException throws if string value
     * isn't a number
     */
    public static Price parsePrice(String price)
            throws NumberFormatException {
        if (price == null) {
            throw new NumberFormatException("null");
        } else if (price.length() == 0) {
            throw new NumberFormatException("length is null");
        }
        return new Price(new BigDecimal(price));
    }

    /**
     * Override method to string
     * @return price in string
     */
    @Override
    public String toString() {
        return price.toString();
    }

    /**
     * Override hash code
     * @return big number hash code
     */
    @Override
    public int hashCode() {
        return price == null ? 0 : price.hashCode();
    }

    /**
     * Override equals method
     * @param obj to check
     * @return is equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Price priceObj = (Price) obj;
        return price.equals(priceObj.price);
    }

    /**
     * Override compare to method
     * @param price to compare
     * @return compare result (int)
     */
    @Override
    public int compareTo(Price price) {
        if (price == null) {
            throw new IllegalArgumentException(
                    "Exception: price is null.");
        }
        return this.price.compareTo(price.price);
    }
}
