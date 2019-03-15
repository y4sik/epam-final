package com.web.securedApp.servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signout")
public class SignOutServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(SignOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("______________________Begin method doPost /signout");
        HttpSession session = req.getSession(false);
        session.invalidate();
        LOGGER.info("______________________End method doPost /signout");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
