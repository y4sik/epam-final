//package com.web.securedApp.controller.filter;
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
//@WebFilter("/home")
//public class HomeFilter implements Filter {
//    private final static Logger LOGGER = Logger.getLogger(HomeFilter.class);
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        LOGGER.info("Begin method doFilter /home_____________");
////        HttpServletRequest request = (HttpServletRequest) servletRequest;
////        HttpServletResponse response = (HttpServletResponse) servletResponse;
////        HttpSession session = request.getSession(false);
////        if (session == null || session.getAttribute("user") == null) {
////            LOGGER.info("End method doFilter, session = null /home_____________");
////            response.sendRedirect(request.getContextPath() + "/login");
//////            request.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
////            return;
////        }
//        LOGGER.info("End method doFilter, session != null /home_____________");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    public void destroy() {
//
//    }
//}
