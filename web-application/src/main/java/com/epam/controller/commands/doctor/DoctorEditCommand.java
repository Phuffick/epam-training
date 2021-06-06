package com.epam.controller.commands.doctor;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.service.implementation.doctor.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * DoctorEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DoctorEditCommand extends Command {

    /**
     * Doctor edit command
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
                DoctorService doctorService
                        = getServiceFactory().getDoctorService();
                Doctor doctor = doctorService.findById(id);
                req.setAttribute("doctor", doctor);
                List<Doctor> doctors = doctorService.findAll();
                req.setAttribute("doctors", doctors);
                req.setAttribute("surgeryId", id);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
