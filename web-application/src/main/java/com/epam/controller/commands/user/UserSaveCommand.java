package com.epam.controller.commands.user;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.doctor.exception.DoctorCreationByIdException;
import com.epam.service.implementation.user.UserService;
import com.epam.service.implementation.user.exception.UserDoesNotExistsException;
import com.epam.service.implementation.user.exception.UserLoginIsNotUniqueException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UserSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class UserSaveCommand extends Command {

    /**
     * Save user command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        user.setLogin(req.getParameter("login"));
        try {
            user.setRole(Role.values()[
                    Integer.parseInt(req.getParameter("role"))]);
        } catch(NumberFormatException
                | ArrayIndexOutOfBoundsException ignored) {}
        if(user.getLogin() != null && user.getRole() != null) {
            try {
                UserService service = getServiceFactory()
                        .getUserService();
                service.save(user);
            } catch(UserLoginIsNotUniqueException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(UserDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        if (Role.DOCTOR.equals(user.getRole())) {
            Doctor doctor = new Doctor();
            doctor.setId(user.getId());
            doctor.setName(req.getParameter("name"));
            try {
                DoctorService service = getServiceFactory()
                        .getDoctorService();
                service.saveFromUserCreation(doctor);
            } catch(DoctorCreationByIdException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/user/users-list.html");
    }
}
