package com.epam.model.medicalparts;

import com.epam.model.Entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Medical card definition class.
 * Containing admission date, discharge date, patient.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class MedicalCard extends Entity {

    /** Date format for calendar output */
    DateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    /** Patient admission to hospital date */
    private GregorianCalendar admissionDate = null;

    /** Patient discharge from hospital date */
    private GregorianCalendar dischargeDate = null;

    /** Patient is given medical card */
    private Long patientId;

    /**
     * Default constructor
     */
    public MedicalCard() {}

    /**
     * Admission date getter
     * @return admission date
     */
    public GregorianCalendar getAdmissionDate() {
        return admissionDate;
    }

    /**
     * Admission date in string getter
     * @return admission date
     */
    public String getAdmissionDateInString() {
        if (admissionDate == null) {
            return "-";
        }
        return format.format(admissionDate.getTime());
    }

    /**
     * Admission date setter (calendar)
     * @param admissionDate to set up
     */
    public void setAdmissionDate(GregorianCalendar admissionDate) {
        this.admissionDate = admissionDate;
    }

    /**
     * Admission date setter (date)
     * @param admissionDate to set up
     */
    public void setAdmissionDate(Date admissionDate) {
        GregorianCalendar calendar
                = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(admissionDate);
        this.admissionDate = calendar;
    }

    /**
     * Discharge date getter
     * @return discharge date
     */
    public GregorianCalendar getDischargeDate() {
        return dischargeDate;
    }

    /**
     * Discharge date in string getter
     * @return discharge date
     */
    public String getDischargeDateInString() {
        if (dischargeDate == null) {
            return "-";
        }
        return format.format(dischargeDate.getTime());
    }

    /**
     * Discharge date setter (calendar)
     * @param dischargeDate to set up
     */
    public void setDischargeDate(GregorianCalendar dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    /**
     * Admission date setter (date)
     * @param dischargeDate to set up
     */
    public void setDischargeDate(Date dischargeDate) {
        GregorianCalendar calendar
                = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(dischargeDate);
        this.dischargeDate = calendar;
    }

    /**
     * Patient id getter
     * @return patient
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * Patient id setter
     * @param patientId to set up
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * Override equals method
     * @param o to check eq case
     * @return are two medical cards equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MedicalCard that = (MedicalCard) o;
        return Objects.equals(admissionDate, that.admissionDate)
                && Objects.equals(dischargeDate, that.dischargeDate)
                && patientId.equals(that.patientId);
    }

    /**
     * Override hash code method
     * @return medical card hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(admissionDate, dischargeDate, patientId);
    }

    /**
     * Override to string method
     * @return medical card in string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MedicalCard{id=").append(getId())
                .append("', admissionDate=").append(admissionDate)
                .append(", dischargeDate=").append(dischargeDate)
                .append(", patient id=").append(patientId);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
