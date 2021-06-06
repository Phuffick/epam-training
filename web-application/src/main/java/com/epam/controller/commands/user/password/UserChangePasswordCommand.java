package com.epam.controller.commands.user.password;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.User;
import com.epam.service.implementation.user.UserService;
import com.epam.service.implementation.user.exception.UserDoesNotExistsException;
import com.epam.service.implementation.user.exception.UserNewPasswordIsIncorrectException;
import com.epam.service.implementation.user.exception.UserPasswordIsIncorrectException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * UserChangePasswordCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserChangePasswordCommand extends Command {

    /**
     * Change password user command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            UserService service = getServiceFactory()
                    .getUserService();
            HttpSession session = req.getSession(false);
            User user = service.findById(((User) session
                    .getAttribute("sessionUser")).getId());
            req.setAttribute("user", user);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
