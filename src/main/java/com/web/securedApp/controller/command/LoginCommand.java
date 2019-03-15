package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.resource.MessageManager;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.service.CustomerService;
import com.web.securedApp.service.UserService;
import com.web.securedApp.service.impl.CustomerServiceImpl;
import com.web.securedApp.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static final String PARAM_NAME_LOGIN = "username";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CHECK_FROM = "checkFrom";
    private UserService userService = new UserServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        String checkFrom = request.getRequestParameter(PARAM_NAME_CHECK_FROM);
        if (checkFrom == null) {
            return page = ConfigurationManager.getProperty("path.page.login");
        }
        String login = request.getRequestParameter(PARAM_NAME_LOGIN);
        String password = request.getRequestParameter(PARAM_NAME_PASSWORD);

        int id = userService.checkLogPass(login, password);
        if (id != -1) {
            HttpSession session = request.getSession(true);
            Customer customer = customerService.getById(id);
            //esli customer = null to vivodit' stranichku s oshibkoi
            session.setAttribute("customer", customer);
            request.setRequestAttribute("user", login);//need to create session
            LOGGER.info("Log In success!");
            HomeCommand command = new HomeCommand();
            page = command.execute(request);
        } else {
            request.setRequestAttribute("errorLogPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
            LOGGER.info("Log In failed!");

        }
        return page;
    }
}
