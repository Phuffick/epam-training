package com.epam.controller.commands.doctor;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.service.implementation.doctor.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DoctorListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DoctorListCommand extends Command {

    /**
     * Doctor list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            DoctorService service = getServiceFactory()
                    .getDoctorService();
            List<Doctor> doctors;
            String parameter = req.getParameter("id");
            if (parameter == null) {
                doctors = service.findAll();
            } else {
                doctors = new ArrayList<>();
                doctors.add(service.findById(
                        Long.parseLong(parameter)));
            }
            req.setAttribute("surgeryId",
                    req.getParameter("medicalCardId"));
            req.setAttribute("doctors", doctors);
            req.setAttribute("canAdd",  parameter == null);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
