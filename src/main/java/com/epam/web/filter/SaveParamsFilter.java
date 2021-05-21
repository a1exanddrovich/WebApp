package com.epam.web.filter;

import com.epam.web.utils.CookieHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveParamsFilter implements Filter {

    private static final String PREVIOUS_PARAMS = "previousParams";

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            if ("GET".equals(httpServletRequest.getMethod())) {
                String query = httpServletRequest.getQueryString();
                CookieHandler cookieHandler = new CookieHandler();
                cookieHandler.addAndUnexpireCookies(httpServletResponse, PREVIOUS_PARAMS, query);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() { }

}
