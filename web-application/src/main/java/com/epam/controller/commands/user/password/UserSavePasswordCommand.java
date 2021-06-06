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
import java.io.IOException;

/**
 * UserSavePasswordCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserSavePasswordCommand extends Command {

    /**
     * Save password user command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            UserService service = getServiceFactory()
                    .getUserService();
            User user = service.findById(Long.parseLong(
                    req.getParameter("id")));
            String newPassword = req.getParameter("new-password");
            String oldPassword = req.getParameter("old-password");
            if(user != null) {
                service.changePassword(user.getId(),
                        oldPassword, newPassword);
            }
        } catch(UserNewPasswordIsIncorrectException
                | UserPasswordIsIncorrectException e) {
            return new Direct("/error.html?message=400",
                    DirectionType.REDIRECT);
        } catch(UserDoesNotExistsException e) {
            return new Direct("/error.html?message=404",
                    DirectionType.REDIRECT);
        } catch(NumberFormatException e) {
            return new Direct("/error.html?message=",
                    DirectionType.REDIRECT);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        return new Direct("/index.html");
    }
}
