package com.epam.controller.commands.medicalcard;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.medicalcard.exception.DischargeDateSettingException;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * MedicalCardSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicalCardSaveCommand extends Command {

    /** Date format */
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy MM dd");

    /**
     * Medical card save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        MedicalCard medicalCard = new MedicalCard();
        try {
            medicalCard.setId(Long.parseLong(req.getParameter("medicalCardId")));
        } catch(NumberFormatException ignored) {}
        medicalCard.setPatientId(Long.parseLong(
                req.getParameter("patientSelect")));
        try {
            Date admissionDate = DATE_FORMAT.parse(req.getParameter(
                    "admissionDate").replace('-', ' '));
            Calendar admissionDateCalendar = Calendar.getInstance();
            admissionDateCalendar.setTime(admissionDate);
            medicalCard.setAdmissionDate(
                    (GregorianCalendar) admissionDateCalendar);
        } catch (ParseException | NullPointerException e) {
            medicalCard.setAdmissionDate((GregorianCalendar) null);
        }
        try {
            Date dischargeDate = DATE_FORMAT.parse(req.getParameter(
                    "dischargeDate").replace('-', ' '));
            Calendar dischargeDateCalendar = Calendar.getInstance();
            dischargeDateCalendar.setTime(dischargeDate);
            medicalCard.setDischargeDate(
                    (GregorianCalendar) dischargeDateCalendar);
        } catch (ParseException | NullPointerException e) {
            medicalCard.setDischargeDate((GregorianCalendar) null);
        }
        try{
            MedicalCardService service = getServiceFactory()
                    .getMedicalCardService();
            service.save(medicalCard);
        } catch(DischargeDateSettingException e) {
            return new Direct("/error.html?message=400",
                    DirectionType.REDIRECT);
        } catch(MedicationDoesNotExistsException e) {
            return new Direct("/error.html?message=404",
                    DirectionType.REDIRECT);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return new Direct("/medicalcard/medical-cards-list.html");
    }
}
