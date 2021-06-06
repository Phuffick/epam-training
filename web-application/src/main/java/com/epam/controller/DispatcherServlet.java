package com.epam.controller;

import com.epam.controller.commands.Command;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.factory.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet definition class.
 * Distinguishes and executes commands
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * Http GET command execute
     */
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        execute(req, resp);
    }

    /**
     * Http POST command execute
     */
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        execute(req, resp);
    }

    /**
     * Service factory getter
     * @return service factory
     */
    public ServiceFactory getServiceFactory() {
        return new ServiceFactoryImpl();
    }

    /**
     * Distinguishes and executes commands
     */
    private void execute(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Command action = CommandFactory.getCommand(url);
        Direct direct = null;
        if(action != null) {
            try(ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory);
                direct = action.execute(req, resp);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        }
        if(direct != null && direct.getDirectionType()
                == DirectionType.REDIRECT) {
            resp.sendRedirect(context + direct.getUrl());
        } else {
            if(direct != null && direct.getUrl() != null) {
                url = direct.getUrl();
            }
            if ("/index".equals(url)) {
                req.getRequestDispatcher(url + ".jsp")
                        .forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp")
                        .forward(req, resp);
            }
        }
    }
}
