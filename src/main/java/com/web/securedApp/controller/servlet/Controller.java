package com.web.securedApp.controller.servlet;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.resource.MessageManager;
import com.web.securedApp.controller.command.ActionCommand;
import com.web.securedApp.controller.command.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("popalo v duGet");
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       LOGGER.info("popalo v duPost");
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        SessionRequestContent requestContent = new SessionRequestContent(request);
        LOGGER.info("comanda defainitsa");
        ActionCommand command = client.defineCommand(requestContent);
        page = command.execute(requestContent);
        if (page != null) {
            LOGGER.info(page);
            //request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);


            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);


            LOGGER.info("posle zakida");
        } else {
            LOGGER.info("POPALO HYI ZNAET KUDA");
            page = ConfigurationManager.getProperty("path.page.error");
            request.getRequestDispatcher(page).forward(request, response);
//            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
//            response.sendRedirect(request.getContextPath() + page);
        }

    }
}
