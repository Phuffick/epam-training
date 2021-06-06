package com.epam.model.medicalparts;

import com.epam.model.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Diagnosis definition class.
 * Containing doctor's name.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Diagnosis extends Entity {

    /** Date format for calendar output */
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    /** Diagnosis description (illness, any other info) */
    private String description;

    /** Date when diagnosis was made */
    private GregorianCalendar date;

    /** Medical card id */
    private Long medicalCardId;

    /** Doctor id */
    private Long doctorId;

    /**
     * Default constructor
     */
    public Diagnosis() {}

    /**
     * Description getter
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Description setter
     * @param description to set up
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Date setter (calendar)
     * @param date to set up
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    /**
     * Date setter (date)
     * @param date to set up
     */
    public void setDate(Date date) {
        GregorianCalendar calendar
                = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        this.date = calendar;
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
     * Doctor id getter
     * @return doctor id
     */
    public Long getDoctorId() {
        return doctorId;
    }

    /**
     * Doctor id setter
     * @param doctorId to set up
     */
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are two diagnosis equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Diagnosis diagnosis = (Diagnosis) o;
        return Objects.equals(description, diagnosis.description)
                && Objects.equals(date, diagnosis.date)
                && Objects.equals(medicalCardId, diagnosis.medicalCardId)
                && Objects.equals(doctorId, diagnosis.doctorId);
    }

    /**
     * Override hash code method
     * @return diagnosis hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(description, date, medicalCardId,
                doctorId);
    }

    /**
     * Override to string method
     * @return diagnosis in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Diagnosis{id=").append(getId())
                .append(", description=").append(description)
                .append(", date=").append(date)
                .append(", medical card id=").append(medicalCardId)
                .append(", doctor id=").append(doctorId).append('}');
        return stringBuilder.toString();
    }
}
