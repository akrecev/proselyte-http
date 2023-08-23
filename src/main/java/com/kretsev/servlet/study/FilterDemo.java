package com.kretsev.servlet.study;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class FilterDemo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String ipAddress = servletRequest.getRemoteAddr();
        String dateTime = LocalDateTime.now().toString();
        System.out.println("\n\n================================\n");
        System.out.println("Request...");
        System.out.println("LocalDateTime: " + dateTime);
        System.out.println("IP:" + ipAddress);
        System.out.println("\n================================\n");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
