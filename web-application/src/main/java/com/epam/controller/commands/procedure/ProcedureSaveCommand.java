package com.epam.controller.commands.procedure;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Procedure;
import com.epam.service.implementation.procedure.ProcedureService;
import com.epam.service.implementation.procedure.exception.ProcedureDateException;
import com.epam.service.implementation.procedure.exception.ProcedureRequiredCountConsumedException;
import com.epam.service.implementation.procedure.exception.ProcedureRequiredCountException;
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
 * ProcedureSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ProcedureSaveCommand extends Command {

    /** Date format */
    private static final DateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy MM dd");

    /**
     * Procedure save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Procedure procedure = new Procedure();
        try {
            procedure.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        procedure.setName(req.getParameter("name"));
        try {
            Date date = DATE_FORMAT.parse(req.getParameter(
                    "date").replace('-', ' '));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            procedure.setDate((GregorianCalendar) calendar);
        } catch (ParseException | NullPointerException e) {
            procedure.setDate(null);
        }
        procedure.setRequiredCount(Integer.parseInt(
                req.getParameter("requiredCount")));
        procedure.setRequiredCountConsumed(Integer.parseInt(
                req.getParameter("requiredCountConsumed")));
        procedure.setMedicalCardId(Long.parseLong(
                req.getParameter("medicalCardSelect")));
        if(procedure.getName() != null
                && procedure.getMedicalCardId() != null
                && procedure.getRequiredCount() != null
                && procedure.getRequiredCountConsumed() != null) {
            try {
                ProcedureService service
                        = getServiceFactory().getProcedureService();
                service.save(procedure);
            } catch(ProcedureDateException
                    | ProcedureRequiredCountException
                    | ProcedureRequiredCountConsumedException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(SurgeryDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/procedure/procedures-list.html?medicalCardId="
                + procedure.getMedicalCardId());
    }
}
