package com.epam.controller.commands;

import com.epam.controller.Direct;
import com.epam.model.actors.User;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceFactoryException;
import com.epam.service.implementation.user.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * LoginCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class LoginCommand extends Command {

    /**
     * Log in command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            try {
                UserService service = getServiceFactory().getUserService();
                User user = service.findByLoginAndPassword(login, password);
                if(user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("sessionUser", user);
                    Cookie[] cookies = req.getCookies();
                    String language = null;
                    String country = null;
                    if (cookies != null) {
                        for(Cookie c: cookies) {
                            if("country".equals(c.getName())) {
                                language = c.getValue();
                            }
                            if("language".equals(c.getName())) {
                                country = c.getValue();
                            }
                        }
                        if (language != null && country != null) {
                            session.setAttribute("language",
                                    new Locale(language, country));
                        }
                    }
                    return new Direct("/index.html");
                } else {
                    return new Direct("/login.html" +
                            "?message=Incorrect login or password");
                }
            } catch(ServiceException | ServiceFactoryException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
