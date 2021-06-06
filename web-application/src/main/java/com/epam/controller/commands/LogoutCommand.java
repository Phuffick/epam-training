package com.epam.controller.commands;

import com.epam.controller.Direct;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LogoutCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class LogoutCommand extends Command {

    /**
     * Log out command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return new Direct("/index.html");
    }
}
