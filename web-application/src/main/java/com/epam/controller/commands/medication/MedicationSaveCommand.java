package com.epam.controller.commands.medication;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Medications;
import com.epam.service.implementation.medications.MedicationsService;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;
import com.epam.service.implementation.medications.exception.MedicationsAmountPerDayCountException;
import com.epam.service.implementation.medications.exception.MedicationsDateException;
import com.epam.service.implementation.medications.exception.MedicationsRequiredCountException;

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
 * MedicationSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationSaveCommand  extends Command {

    /** Date format */
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy MM dd");

    /**
     * Medication save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Medications medications = new Medications();
        try {
            medications.setId(Long.parseLong(
                    req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        medications.setName(req.getParameter("name"));
        medications.setRequiredCount(Integer.parseInt(
                req.getParameter("requiredCount")));
        medications.setAmountPerDay(Integer.parseInt(
                req.getParameter("amountPerDay")));
        try {
            Date date = DATE_FORMAT.parse(req.getParameter(
                    "date").replace('-', ' '));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            medications.setDate((GregorianCalendar) calendar);
        } catch (ParseException | NullPointerException ignored) {
            medications.setDate(null);
        }
        medications.setDone("canceled"
                .equals(req.getParameter("status")));
        medications.setMedicalCardId(Long.parseLong(
                req.getParameter("medicalCardSelect")));
        if(medications.getName() != null
                && medications.getDone() != null
                && medications.getAmountPerDay() != null
                && medications.getRequiredCount() != null
                && medications.getMedicalCardId() != null) {
            try {
                MedicationsService service = getServiceFactory()
                        .getMedicationsService();
                service.save(medications);
            } catch(MedicationsDateException
                    | MedicationsRequiredCountException
                    | MedicationsAmountPerDayCountException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(MedicationDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/medication/medications-list.html?medicalCardId="
                + medications.getMedicalCardId());
    }
}
