package com.epam.controller.commands.patient;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Patient;
import com.epam.service.implementation.patient.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * PatientEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class PatientEditCommand extends Command {

    /**
     * Patient edit command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException ignored) {
        }
        if (id != null) {
            try {
                PatientService patientService
                        = getServiceFactory().getPatientService();
                Patient patient = patientService.findById(id);
                req.setAttribute("patient", patient);
                List<Patient> patients = patientService.findAll();
                req.setAttribute("patients", patients);
                String medicalCardId
                        = req.getParameter("medicalCardId");
                if (medicalCardId != null) {
                    req.setAttribute("medicalCardId", Long.parseLong(
                            medicalCardId));
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
