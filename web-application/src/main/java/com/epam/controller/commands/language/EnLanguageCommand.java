package com.epam.controller.commands.language;

import com.epam.controller.Direct;
import com.epam.controller.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * EnLanguageCommand definition class.
 * Sets en localization
 *
 * @version    1.0.0 25 May 2021
 * @author     Belyack Maxim
 */
public class EnLanguageCommand extends Command {

    /**
     * Setting localization method
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Locale locale = new Locale("en", "EN");
        HttpSession session = req.getSession();
        session.setAttribute("language", locale);
        ResourceBundle.getBundle("messages", locale);
        Cookie languageCookie
                = new Cookie("language", locale.getLanguage());
        Cookie countryCookie
                = new Cookie("country", locale.getCountry());
        resp.addCookie(languageCookie);
        resp.addCookie(countryCookie);
        return new Direct("/index.html");
    }
}
