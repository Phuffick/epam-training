package com.epam.util.filter;

import com.epam.model.actors.User;
import com.epam.model.actors.properties.Role;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SecurityFilter definition class.
 * Makes dependencies between page and roles
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class SecurityFilter implements Filter {

    /** Map of page to role */
    private static final Map<String, Set<Role>> permissions
            = new HashMap<>();

    static {
        Set<Role> all = new HashSet<>(Arrays.asList(Role.values()));
        Set<Role> admin = new HashSet<>();
        admin.add(Role.ADMINISTRATOR);
        Set<Role> hospitalStaff = new HashSet<>();
        hospitalStaff.add(Role.DOCTOR);
        hospitalStaff.add(Role.NURSE);

        permissions.put("/logout", all);
        permissions.put("/user/password/save-password", all);

        permissions.put("/user/users-list", admin);
        permissions.put("/user/users-edit", admin);
        permissions.put("/user/user-profile", all);
        permissions.put("/user/save", admin);
        permissions.put("/user/delete", admin);

        permissions.put("/diagnosis/diagnosis-list", hospitalStaff);
        permissions.put("/diagnosis/diagnosis-list-archive", hospitalStaff);
        permissions.put("/diagnosis/diagnosis-edit", hospitalStaff);
        permissions.put("/diagnosis/save", hospitalStaff);

        permissions.put("/medicalcard/medical-cards-list", hospitalStaff);
        permissions.put("/medicalcard/medical-cards-list-archive", hospitalStaff);
        permissions.put("/medicalcard/medical-cards-edit", hospitalStaff);
        permissions.put("/medicalcard/save", hospitalStaff);

        permissions.put("/medication/medications-list", hospitalStaff);
        permissions.put("/medication/medications-list-archive", hospitalStaff);
        permissions.put("/medication/medications-edit", hospitalStaff);
        permissions.put("/medication/save", hospitalStaff);

        permissions.put("/procedure/procedures-list", hospitalStaff);
        permissions.put("/procedure/procedures-list-archive", hospitalStaff);
        permissions.put("/procedure/procedures-edit", hospitalStaff);
        permissions.put("/procedure/save", hospitalStaff);

        permissions.put("/surgery/surgeries-list", hospitalStaff);
        permissions.put("/surgery/surgeries-list-archive", hospitalStaff);
        permissions.put("/surgery/surgeries-edit", hospitalStaff);
        permissions.put("/surgery/save", hospitalStaff);

        permissions.put("/doctor/doctors-list", hospitalStaff);
        permissions.put("/doctor/doctors-edit", hospitalStaff);
        permissions.put("/doctor/save", hospitalStaff);

        permissions.put("/nurse/nurses-list", hospitalStaff);

        permissions.put("/patient/patients-list", hospitalStaff);
        permissions.put("/patient/patients-list-archive", hospitalStaff);
        permissions.put("/patient/patients-edit", hospitalStaff);
        permissions.put("/patient/save", hospitalStaff);
    }

    /**
     * Do filter method
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        String url = httpReq.getRequestURI();
        String context = httpReq.getContextPath();
        int postfixIndex = url.lastIndexOf(".html");
        if(postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex);
        } else {
            url = url.substring(context.length());
        }
        Set<Role> roles = permissions.get(url);
        if(roles != null) {
            HttpSession session = httpReq.getSession(false);
            if(session != null) {
                User user
                        = (User) session.getAttribute("sessionUser");
                if(user != null && roles.contains(user.getRole())) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        } else {
            chain.doFilter(req, resp);
            return;
        }
        httpResp.sendRedirect(context
                + "/login.html?message=Authorise to reach this page");
    }
}
