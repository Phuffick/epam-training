package com.epam.controller.commands.surgery;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Surgery;
import com.epam.service.implementation.surgery.SurgeryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * SurgeryListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SurgeryListCommand extends Command {

    /**
     * Surgeries list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            SurgeryService service = getServiceFactory()
                    .getSurgeryService();
            List<Surgery> surgeries;
            String parameter = req.getParameter("medicalCardId");
            req.setAttribute("medicalCardId", parameter);
            if (parameter == null) {
                surgeries = service.findAll();
            } else {
                surgeries = service.findByMedicalCardId(
                        Long.parseLong(parameter));
            }
            req.setAttribute("surgeries", surgeries);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
