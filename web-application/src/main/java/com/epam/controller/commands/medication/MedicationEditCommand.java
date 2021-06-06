package com.epam.controller.commands.medication;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.model.actors.User;
import com.epam.model.medicalparts.Medications;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.medications.MedicationsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * MedicationEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicationEditCommand extends Command {

    /**
     * Medication edit command
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
                MedicationsService medicationService
                        = getServiceFactory().getMedicationsService();
                Medications medication = medicationService.findById(id);
                req.setAttribute("medications", medication);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("sessionUser");
            DoctorService doctorService
                    = getServiceFactory().getDoctorService();
            Doctor doctor = doctorService.findById(user.getId());
            req.setAttribute("doctor", doctor);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
