package com.epam.controller.commands.surgery;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.model.actors.User;
import com.epam.model.medicalparts.Surgery;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.surgery.SurgeryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SurgeryEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SurgeryEditCommand extends Command {

    /**
     * Surgery edit command
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
        } catch (NumberFormatException ignored) {}
        if (id != null) {
            try {
                SurgeryService surgeryService
                        = getServiceFactory().getSurgeryService();
                Surgery surgery = surgeryService.findById(id);
                req.setAttribute("surgery", surgery);
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
