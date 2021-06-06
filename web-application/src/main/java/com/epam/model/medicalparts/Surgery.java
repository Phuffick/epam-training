package com.epam.model.medicalparts;

import com.epam.model.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Surgery definition class.
 * Containing procedure's name, planing date, is done field,
 * medical card, doctor.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class Surgery extends Entity {

    /** Date format for calendar output */
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    /** Surgery's name */
    private String name;

    /** Surgery's planning date */
    private GregorianCalendar planingDate;

    /** Is surgery done */
    private Boolean isDone;

    /** Medical card id */
    private Long medicalCardId;

    /** Doctor id */
    private Long doctorId;

    /**
     * Default constructor
     */
    public Surgery() {}

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
     * Planning date getter
     * @return planning date
     */
    public GregorianCalendar getPlaningDate() {
        return planingDate;
    }

    /**
     * Date in string getter
     * @return date
     */
    public String getPlaningDateInString() {
        return format.format(planingDate.getTime());
    }

    /**
     * Planning date setter
     * @param planingDate to set up
     */
    public void setPlaningDate(GregorianCalendar planingDate) {
        this.planingDate = planingDate;
    }

    /**
     * Surgery status getter
     * @return surgery status
     */
    public Boolean getDone() {
        return isDone;
    }

    /**
     * Set surgery done or not
     * @param done to set
     */
    public void setDone(Boolean done) {
        isDone = done;
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
     * @return are two surgeries equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Surgery surgery = (Surgery) o;
        return Objects.equals(name, surgery.name)
                && Objects.equals(planingDate, surgery.planingDate)
                && Objects.equals(isDone, surgery.isDone)
                && Objects.equals(doctorId, surgery.doctorId)
                && Objects.equals(medicalCardId, surgery.medicalCardId);
    }

    /**
     * Override hash code method
     * @return surgery hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, planingDate, isDone, doctorId,
                medicalCardId);
    }

    /**
     * Override to string method
     * @return surgery in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Surgery{id=").append(getId())
                .append(", name=").append(name)
                .append(", planingDate=").append(planingDate)
                .append(", isDone=").append(isDone)
                .append(", doctor=").append(doctorId)
                .append(", medical card id=").append(medicalCardId)
                .append('}');
        return stringBuilder.toString();
    }
}
