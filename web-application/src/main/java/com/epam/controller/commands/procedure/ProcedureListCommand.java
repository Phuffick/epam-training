package com.epam.controller.commands.procedure;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Procedure;
import com.epam.service.implementation.procedure.ProcedureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ProcedureListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class ProcedureListCommand extends Command {

    /**
     * Procedure list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            ProcedureService service = getServiceFactory()
                    .getProcedureService();
            List<Procedure> procedures;
            String parameter = req.getParameter("medicalCardId");
            req.setAttribute("medicalCardId", parameter);
            if (parameter == null) {
                procedures = service.findAll();
            } else {
                procedures = service.findByMedicalCardId(
                        Long.parseLong(parameter));
            }
            req.setAttribute("procedures", procedures);
            return null;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
