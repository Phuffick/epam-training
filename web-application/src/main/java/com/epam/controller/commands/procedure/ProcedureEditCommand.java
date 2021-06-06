package com.epam.controller.commands.procedure;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Procedure;
import com.epam.service.implementation.procedure.ProcedureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ProcedureEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ProcedureEditCommand extends Command {

    /**
     * Procedure edit command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            req.setAttribute("medicalCardId",
                    req.getParameter("medicalCardId"));
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }
        if (id != null) {
            try {
                ProcedureService surgeryService
                        = getServiceFactory().getProcedureService();
                Procedure procedure = surgeryService.findById(id);
                req.setAttribute("procedure", procedure);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
