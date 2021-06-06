package com.epam.controller.commands.patient;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Patient;
import com.epam.service.implementation.patient.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PatientListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class PatientListCommand extends Command {

    /**
     * Patient list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            PatientService service = getServiceFactory()
                    .getPatientService();
            List<Patient> patients;
            String parameter = req.getParameter("patientId");
            if (parameter == null) {
                patients = service.findAll();
            } else {
                patients = new ArrayList<>();
                patients.add(
                        service.findById(Long.parseLong(parameter)));
            }
            req.setAttribute("medicalCardId",
                    req.getParameter("medicalCardId"));
            req.setAttribute("patients", patients);
            req.setAttribute("canAdd",  parameter == null);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
