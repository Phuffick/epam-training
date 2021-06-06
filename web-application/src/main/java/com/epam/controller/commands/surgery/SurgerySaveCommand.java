package com.epam.controller.commands.surgery;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Surgery;
import com.epam.service.implementation.surgery.SurgeryService;
import com.epam.service.implementation.surgery.exception.SurgeryDateException;
import com.epam.service.implementation.surgery.exception.SurgeryDoesNotExistsException;
import com.epam.service.implementation.user.exception.UserDoesNotExistsException;

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
 * SurgerySaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SurgerySaveCommand extends Command {

    /** Date format */
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy MM dd");

    /**
     * Surgeries save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Surgery surgery = new Surgery();
        try {
            surgery.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        surgery.setName(req.getParameter("name"));
        try {
            Date date = DATE_FORMAT.parse(req.getParameter(
                    "plannedDate").replace('-', ' '));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            surgery.setPlaningDate((GregorianCalendar) calendar);
        } catch (ParseException | NullPointerException ignored) {
            surgery.setPlaningDate(null);
        }
        surgery.setDone("canceled"
                .equals(req.getParameter("status")));
        surgery.setMedicalCardId(Long.parseLong(
                req.getParameter("medicalCardSelect")));
        surgery.setDoctorId(Long.parseLong(
                req.getParameter("doctorSelect")));
        if(surgery.getName() != null && surgery.getDone() != null) {
            try {
                SurgeryService service = getServiceFactory()
                        .getSurgeryService();
                service.save(surgery);
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
        return new Direct("/surgery/surgeries-list.html?medicalCardId="
                + surgery.getMedicalCardId());
    }
}
