package com.example.chat.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS filter.
 *
 * @author Alexey Voronin.
 * @since 21.10.2019.
 */
public class CorsFilter implements Filter {

    /**
     * Simple cors filter.
     *
     * @param req   request.
     * @param res   response.
     * @param chain filter chain.
     * @throws IOException      exception.
     * @throws ServletException exception.
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
