package com.epam.controller.commands.user;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.surgery.SurgeryService;
import com.epam.service.implementation.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserEditCommand extends Command {

    /**
     * Edit user command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch(NumberFormatException ignored) {}
        boolean userCanBeDeleted = false;
        if(id != null) {
            try {
                UserService service = getServiceFactory()
                        .getUserService();
                User user = service.findById(id);
                req.setAttribute("user", user);

                if (Role.DOCTOR.equals(user.getRole())) {
                    DoctorService doctorService = getServiceFactory()
                            .getDoctorService();
                    userCanBeDeleted
                            = doctorService.canBeDeleted(user.getId());
                } else if (Role.NURSE.equals(user.getRole())) {
                    userCanBeDeleted = true;
                }
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("roles", Role.values());
        req.setAttribute("userCanBeDeleted", userCanBeDeleted);
        return null;
    }
}
