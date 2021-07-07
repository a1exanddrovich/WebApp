package com.epam.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private final static String ENCODING = "requestEncoding";

    private String requestEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        requestEncoding = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(requestEncoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() { }

}
