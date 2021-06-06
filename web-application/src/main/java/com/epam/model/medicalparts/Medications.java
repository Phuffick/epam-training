package com.epam.model.medicalparts;

import com.epam.model.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Medications definition class.
 * Containing medications name, is done (medication course is canceled),
 * required count, amount per day, medical card.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Medications extends Entity {

    /** Date format for calendar output */
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    /** Medications name */
    private String name;

    /** Is medication course canceled  */
    private Boolean isDone;

    /** Required count of medications */
    private Integer requiredCount;

    /** Required amount of medications per day to consume */
    private Integer amountPerDay;

    /** Medications begin date */
    private GregorianCalendar date;

    /** Medical card id */
    private Long medicalCardId;

    /**
     * Default constructor
     */
    public Medications() {}

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
     * Course status getter
     * @return course status
     */
    public Boolean getDone() {
        return isDone;
    }

    /**
     * Course status setter
     * @param done to set up
     */
    public void setDone(Boolean done) {
        isDone = done;
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
     * Amount per day getter
     * @return amount per day
     */
    public Integer getAmountPerDay() {
        return amountPerDay;
    }

    /**
     * Amount per day setter
     * @param amountPerDay to set up
     */
    public void setAmountPerDay(Integer amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    /**
     * Date getter
     * @return date
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
     * @return are two medications equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Medications that = (Medications) o;
        return Objects.equals(name, that.name)
                && Objects.equals(isDone, that.isDone)
                && Objects.equals(requiredCount, that.requiredCount)
                && Objects.equals(amountPerDay, that.amountPerDay)
                && Objects.equals(date, that.date)
                && Objects.equals(medicalCardId, that.medicalCardId);
    }
    /**
     * Override hash code method
     * @return medications hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, isDone, requiredCount,
                amountPerDay, date, medicalCardId);
    }

    /**
     * Override to string method
     * @return medications in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Medications{id=").append(getId())
                .append(", name=").append(name).append(", isDone=")
                .append(isDone).append(", requiredCount=")
                .append(requiredCount).append(", amountPerDay=")
                .append(amountPerDay).append(", date=").append(date)
                .append(", medical card id=").append(medicalCardId)
                .append('}');
        return stringBuilder.toString();
    }
}
