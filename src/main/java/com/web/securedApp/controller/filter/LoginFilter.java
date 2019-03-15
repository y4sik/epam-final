//package com.web.securedApp.controller.filter;
//
//
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/login")
//public class LoginFilter implements Filter {
//    private final static Logger LOGGER = Logger.getLogger(LoginFilter.class);
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        LOGGER.info("Begin method doFilter /login");
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
////        HttpSession session = request.getSession(false);
////        if (session != null  && session.getAttribute("user") != null) {
////            LOGGER.info("End method doFilter, session != null /login");
////            response.sendRedirect(request.getContextPath() + "/home");
////            return;
////        }
//        LOGGER.info("End method doFilter, session = null /login");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    public void destroy() {
//
//    }
//}
