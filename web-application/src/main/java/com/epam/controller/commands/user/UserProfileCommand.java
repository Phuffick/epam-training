package com.epam.controller.commands.user;

import com.epam.controller.Direct;
import com.epam.controller.commands.Command;
import com.epam.model.actors.Doctor;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.implementation.doctor.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserProfileCommand extends Command {

    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("sessionUser");
        req.setAttribute("user", user);
        if (Role.DOCTOR.equals(user.getRole())) {
            try {
                DoctorService doctorService
                        = getServiceFactory().getDoctorService();
                Doctor doctor = doctorService.findById(user.getId());
                req.setAttribute("doctor", doctor);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return null;
    }
}
