package com.project.gamelibrary.config.filter;

import com.project.gamelibrary.Handler.MultiReadHttpServletRequest;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Myfilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MultiReadHttpServletRequest multiReadHttpServletRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
        chain.doFilter(multiReadHttpServletRequest, response);
    }
}
