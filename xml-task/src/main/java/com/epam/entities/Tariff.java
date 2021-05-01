package com.epam.entities;

import com.epam.entities.tariffproperties.Price;
import com.epam.entities.tariffproperties.TarifficationType;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Tariff definition class
 *
 * @version    1.0.0 24 April 2021
 * @author     Belyack Maxim
 */
public class Tariff {

    /** Tariff's id */
    private String id = "";

    /** Tariff's type */
    private String type = "";

    /** Tariff's operator */
    private String operator = "";

    /** Tariff's month payroll */
    private Price monthPayroll = new Price();

    /** Tariff's price of in-network calls */
    private Price inNetworkCallsPrice = new Price();

    /** Tariff's price of out-network calls */
    private Price outNetworkCallsPrice = new Price();

    /** Tariff's price of landlines calls */
    private Price landlinesCallsPrice = new Price();

    /** Tariff's SMS price */
    private Price smsPrice = new Price();

    /** Tariff's favorite cell numbers */
    private Set<String> favoriteCellNumbers
            = new LinkedHashSet<>();

    /** Tariff's tariffication type */
    private TarifficationType tarifficationType
            = TarifficationType.TWELVE_SECONDS_TARIFFICATION;

    /** Tariff's connection price */
    private Price connectionPrice = new Price();

    /**
     * Tariff id setter
     * @param id to set up
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Tariff's id getter
     * @return tariff's id
     */
    public String getId() {
        return id;
    }

    /**
     * Tariff's name getter
     * @return tariff's name
     */
    public String getType() {
        return type;
    }

    /**
     * Tariff's name setter
     * @param name tariff's name
     */
    public void setType(String name) {
        this.type = name;
    }

    /**
     * Tariff's operator getter
     * @return tariff's operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Tariff's operator setter
     * @param operator tariff's operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * Tariff's operator getter
     * @return tariff's operator
     */
    public String getMonthPayroll() {
        return monthPayroll.toString();
    }

    /**
     * Tariff's month payroll setter
     * @param monthPayroll tariff's month payroll
     */
    public void setMonthPayroll(Price monthPayroll) {
        this.monthPayroll = monthPayroll;
    }

    /**
     * Tariff's in-network calls price getter
     * @return tariff's in-network calls price
     */
    public String getInNetworkCallsPrice() {
        return inNetworkCallsPrice.toString();
    }

    /**
     * Tariff's in-network calls price setter
     * @param inNetworkCallsPrice tariff's in-network calls price
     */
    public void setInNetworkCallsPrice(Price inNetworkCallsPrice) {
        this.inNetworkCallsPrice = inNetworkCallsPrice;
    }

    /**
     * Tariff's out-network calls price getter
     * @return tariff's out-network calls price
     */
    public String getOutNetworkCallsPrice() {
        return outNetworkCallsPrice.toString();
    }

    /**
     * Tariff's out-network calls price setter
     * @param outNetworkCallsPrice tariff's out-network calls price
     */
    public void setOutNetworkCallsPrice(Price outNetworkCallsPrice) {
        this.outNetworkCallsPrice = outNetworkCallsPrice;
    }

    /**
     * Tariff's landlines calls price getter
     * @return tariff's landlines calls price
     */
    public String getLandlinesCallsPrice() {
        return landlinesCallsPrice.toString();
    }

    /**
     * Tariff's landlines calls price setter
     * @param landlinesCallsPrice tariff's landlines calls price
     */
    public void setLandlinesCallsPrice(Price landlinesCallsPrice) {
        this.landlinesCallsPrice = landlinesCallsPrice;
    }

    /**
     * Tariff's sms price getter
     * @return tariff's sms price
     */
    public String getSmsPrice() {
        return smsPrice.toString();
    }

    /**
     * Tariff's sms price setter
     * @param smsPrice tariff's sms price
     */
    public void setSmsPrice(Price smsPrice) {
        this.smsPrice = smsPrice;
    }

    /**
     * Favorite cell numbers list getter
     * @return favorite cell numbers list
     */
    public Set<String> getFavoriteCellNumbers() {
        return favoriteCellNumbers;
    }

    /**
     * Tariffication type getter
     * @return tariffication type
     */
    public String getTarifficationType() {
        return tarifficationType.toString();
    }

    /**
     * Tariffication type setter
     * @param tarifficationType to set up
     */
    public void setTarifficationType(
            TarifficationType tarifficationType) {
        this.tarifficationType = tarifficationType;
    }

    /**
     * Connection type getter
     * @return connection type
     */
    public String getConnectionPrice() {
        return connectionPrice.toString();
    }

    /**
     * Connection type setter
     * @param connectionPrice to set up
     */
    public void setConnectionPrice(Price connectionPrice) {
        this.connectionPrice = connectionPrice;
    };

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
        Tariff tariff = (Tariff) obj;
        if (favoriteCellNumbers.size()
                != tariff.favoriteCellNumbers.size()) {
            return false;
        }
        if (!favoriteCellNumbers.equals(tariff.favoriteCellNumbers)) {
            return false;
        }
        return id.equals(tariff.id) && type.equals(tariff.type)
                && operator.equals(tariff.operator)
                && monthPayroll.equals(tariff.monthPayroll)
                && inNetworkCallsPrice.equals(tariff.inNetworkCallsPrice)
                && outNetworkCallsPrice.equals(tariff.outNetworkCallsPrice)
                && landlinesCallsPrice.equals(tariff.landlinesCallsPrice)
                && smsPrice.equals(tariff.smsPrice)
                && tarifficationType.equals(tariff.tarifficationType)
                && connectionPrice.equals(tariff.connectionPrice);
    }

    /**
     * Override to string method
     * @return tariff in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tariff info:\nName: ")
                .append(getType()).append('\n')
                .append("Operator name: ")
                .append(getOperator()).append('\n')
                .append("Month payroll: ")
                .append(getMonthPayroll()).append('\n')
                .append("In network calls price: ")
                .append(getInNetworkCallsPrice()).append('\n')
                .append("Out network calls price: ")
                .append(getOutNetworkCallsPrice()).append('\n')
                .append("Landlines network calls price: ")
                .append(getLandlinesCallsPrice()).append('\n')
                .append("SMS price: ").append(getSmsPrice()).append('\n');
        for (String cellNumber : getFavoriteCellNumbers()) {
            stringBuilder.append("Favorite number: ")
                    .append(cellNumber).append('\n');
        }
        stringBuilder.append("Tariffication: ")
                .append(getTarifficationType()).append('\n')
                .append("Connection price: ")
                .append(getConnectionPrice());
        return stringBuilder.toString();
    }
}
