package com.epam.controller.commands.user;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceFactoryException;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserDeleteCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserDeleteCommand extends Command {

    /**
     * Delete user list command
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
        if(id != null) {
            try {
                UserService service = getServiceFactory()
                        .getUserService();
                User user = service.findById(id);
                if (Role.DOCTOR.equals(user.getRole())) {
                    DoctorService doctorService = getServiceFactory()
                            .getDoctorService();
                    doctorService.delete(id);
                }
                service.delete(id);
            } catch(ServiceException | ServiceFactoryException e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/user/users-list.html");
    }
}
