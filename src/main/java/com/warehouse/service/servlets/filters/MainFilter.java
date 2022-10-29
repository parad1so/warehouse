package com.warehouse.service.servlets.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@WebFilter("/window/*")*/
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession(false);
        Cookie[] cookies = servletRequest.getCookies();
        int count = 0;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("Login")) {
                    count++;
                }
            }
        }
        if (session == null || session.getAttribute("userAccount") == null || count == 0) {

            servletResponse.sendRedirect("/");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
