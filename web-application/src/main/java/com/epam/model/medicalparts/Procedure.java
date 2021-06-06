package com.epam.model.medicalparts;

import com.epam.model.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Procedure definition class.
 * Containing procedure's name, required count,
 * required count done, medical card.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Procedure extends Entity {

    /** Date format for calendar output */
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    /** Procedure's name */
    private String name;

    /** Required count of procedure doing */
    private Integer requiredCount;

    /** Required count of procedure doing that has already done */
    private Integer requiredCountConsumed;

    /** Procedure's begin date */
    private GregorianCalendar date;

    /** Medical card id */
    private Long medicalCardId;

    /**
     * Default constructor
     */
    public Procedure() {}

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
     * Required count getter
     * @return required count
     */
    public Integer getRequiredCount() {
        return requiredCount;
    }

    /**
     * Required count setter
     * @param requiredCount to set up
     */
    public void setRequiredCount(Integer requiredCount) {
        this.requiredCount = requiredCount;
    }

    /**
     * Required count consumed getter
     * @return required count consumed
     */
    public Integer getRequiredCountConsumed() {
        return requiredCountConsumed;
    }

    /**
     * Required count consumed setter
     * @param requiredCountConsumed to set up
     */
    public void setRequiredCountConsumed(Integer requiredCountConsumed) {
        this.requiredCountConsumed = requiredCountConsumed;
    }

    /**
     * Date getter
     * @return gate
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * Date in string getter
     * @return date
     */
    public String getDateInString() {
        return format.format(date.getTime());
    }

    /**
     * Date setter
     * @param date to set up
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /**
     * Medical card id getter
     * @return medical card id
     */
    public Long getMedicalCardId() {
        return medicalCardId;
    }

    /**
     * Medical card id setter
     * @param medicalCardId to set up
     */
    public void setMedicalCardId(Long medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are two procedures equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Procedure procedure = (Procedure) o;
        return Objects.equals(name, procedure.name)
                && Objects.equals(requiredCount, procedure.requiredCount)
                && Objects.equals(
                        requiredCountConsumed, procedure.requiredCountConsumed)
                && Objects.equals(date, procedure.date)
                && Objects.equals(medicalCardId, procedure.medicalCardId);
    }

    /**
     * Override hash code method
     * @return procedure hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, requiredCount,
                requiredCountConsumed, date, medicalCardId);
    }

    /**
     * Override to string method
     * @return procedure in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Procedure{id=").append(getId())
                .append(", name=").append(name)
                .append(", requiredCount=").append(requiredCount)
                .append(", requiredCountConsumed=")
                .append(requiredCountConsumed).append(", date=")
                .append(date).append(", medical card id=")
                .append(medicalCardId).append('}');
        return stringBuilder.toString();
    }
}
