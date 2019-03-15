//package com.web.securedApp.servlets;
//
//import com.web.securedApp.controller.google.captcha.VerifyRecaptcha;
//import com.web.securedApp.model.dao.connector.ConnectionFactory;
//import com.web.securedApp.model.domain.customer.User;
//import com.web.securedApp.service.MyService;
//import org.apache.log4j.Logger;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.Connection;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//
//    private final static Logger LOGGER = Logger.getLogger(LoginServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        LOGGER.info("Begin in doPost /login____________________________");
//
//        String userName = req.getParameter("user");
//        String password = req.getParameter("pass");
//        String errorString = null;
//        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
//
//        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);
//        boolean isExist = MyService.isExist(userName, password);
//
//        LOGGER.info(userName + " " + password);
//        Connection connection = ConnectionFactory.getConnection();
//        if (isExist && verify) {
//            HttpSession session = req.getSession();
//            session.setAttribute("user", new User(userName, password));
//            resp.sendRedirect(req.getContextPath() + "/home");
//        } else if (verify) {
//            errorString = "Either username or password is wrong.";
//            req.setAttribute("errorString", errorString);
//            //resp.sendRedirect(req.getContextPath() + "/login");
//            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
//        } else if (isExist) {
//            errorString = "You missed the Captcha.";
//            req.setAttribute("errorString", errorString);
//            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
//        } else {
//            errorString = "Captcha or login or password are invalid!";
//            req.setAttribute("errorString", errorString);
//            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
//        }
//
//
//        ConnectionFactory.closeConnection(connection);
//
//        LOGGER.info("End from doPost /login____________________________");
//    }
//}