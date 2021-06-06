package com.epam.controller.commands.medication;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.medicalparts.Medications;
import com.epam.service.implementation.medications.MedicationsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * MedicationListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationListCommand  extends Command {

    /**
     * Medication list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            MedicationsService service
                    = getServiceFactory().getMedicationsService();
            List<Medications> medications;
            String parameter = req.getParameter("medicalCardId");
            req.setAttribute("medicalCardId", parameter);
            if (parameter == null) {
                medications = service.findAll();
            } else {
                medications = service.findByMedicalCardId(
                        Long.parseLong(parameter));
            }
            req.setAttribute("medications", medications);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
