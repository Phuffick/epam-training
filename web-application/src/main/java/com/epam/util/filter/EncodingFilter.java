package com.epam.util.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * EncodingFilter definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class EncodingFilter implements Filter {

    /**
     * Encoding filter do method
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }
}
