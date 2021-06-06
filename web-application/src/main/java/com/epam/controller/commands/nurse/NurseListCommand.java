package com.epam.controller.commands.nurse;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.implementation.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * NurseListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class NurseListCommand extends Command {

    /**
     * Nurse list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            UserService service = getServiceFactory()
                    .getUserService();
            List<User> nurses = service.findByRole(Role.NURSE);
            req.setAttribute("nurses", nurses);
            return null;
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
