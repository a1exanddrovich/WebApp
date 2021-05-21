package com.epam.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    private final static String LOCALE_SESSION = "javax.servlet.jsp.jstl.fmt.locale";
    private final static String LANGUAGE = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getParameter(LOCALE_SESSION) != null) {
            request.getSession().setAttribute(LANGUAGE, request.getParameter(LOCALE_SESSION));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

