package com.epam.controller.commands.diagnosis;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Diagnosis;
import com.epam.service.implementation.diagnosis.DiagnosisService;
import com.epam.service.implementation.surgery.exception.SurgeryDateException;
import com.epam.service.implementation.surgery.exception.SurgeryDoesNotExistsException;

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
 * DiagnosisSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DiagnosisSaveCommand extends Command {

    /** Date format */
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy MM dd");

    /**
     * Diagnosis save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Diagnosis diagnosis = new Diagnosis();
        try {
            diagnosis.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        diagnosis.setDescription(req.getParameter("description"));
        try {
            Date date = DATE_FORMAT.parse(req.getParameter(
                    "date").replace('-', ' '));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            diagnosis.setDate((GregorianCalendar) calendar);
        } catch (ParseException | NullPointerException e) {
            diagnosis.setDate((GregorianCalendar) null);
        }
        diagnosis.setMedicalCardId(Long.parseLong(
                req.getParameter("medicalCardSelect")));
        diagnosis.setDoctorId(Long.parseLong(
                req.getParameter("doctorSelect")));
        if(diagnosis.getDescription() != null
                && diagnosis.getMedicalCardId() != null
                && diagnosis.getDoctorId() != null) {
            try {
                DiagnosisService service
                        = getServiceFactory().getDiagnosisService();
                service.save(diagnosis);
            } catch(SurgeryDateException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(SurgeryDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/diagnosis/diagnosis-list.html?medicalCardId="
                + diagnosis.getMedicalCardId());
    }
}
