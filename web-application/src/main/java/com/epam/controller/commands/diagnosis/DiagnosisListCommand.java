package com.epam.controller.commands.diagnosis;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Diagnosis;
import com.epam.service.implementation.diagnosis.DiagnosisService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * DiagnosisListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DiagnosisListCommand extends Command {

    /**
     * Diagnosis list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            DiagnosisService service = getServiceFactory()
                    .getDiagnosisService();
            List<Diagnosis> diagnoses;
            String parameter = req.getParameter("medicalCardId");
            req.setAttribute("medicalCardId", parameter);
            if (parameter == null) {
                diagnoses = service.findAll();
            } else {
                diagnoses = service.findByMedicalCardId(
                        Long.parseLong(parameter));
            }
            req.setAttribute("diagnoses", diagnoses);
            return null;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
