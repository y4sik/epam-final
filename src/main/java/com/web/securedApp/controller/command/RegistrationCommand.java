package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.resource.MessageManager;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.service.UserService;
import com.web.securedApp.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class RegistrationCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private UserService userService = new UserServiceImpl();

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_SURNAME = "surname";
    private static final String PARAM_NAME_GENDER = "gender";
    private static final String PARAM_NAME_DATE_OF_BIRTHDAY = "date";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PHONE_NUMBER = "phone";
    private static final String PARAM_NAME_CHECK_FROM = "checkFrom";

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        String checkFrom = request.getRequestParameter(PARAM_NAME_CHECK_FROM);
        if (checkFrom == null) {
            return page = ConfigurationManager.getProperty("path.page.registration");
        }
        String name = request.getRequestParameter(PARAM_NAME_NAME);
        LOGGER.info("name=" + name);
        String surname = request.getRequestParameter(PARAM_NAME_SURNAME);
        String gender = request.getRequestParameter(PARAM_NAME_GENDER);

        LOGGER.info("Date=" + request.getRequestParameter(PARAM_NAME_DATE_OF_BIRTHDAY));
        LocalDate dateOfBirthday = LocalDate.parse(request.getRequestParameter(PARAM_NAME_DATE_OF_BIRTHDAY));
        String email = request.getRequestParameter(PARAM_NAME_EMAIL);
        String password = request.getRequestParameter(PARAM_NAME_PASSWORD);
        String phoneNumber = request.getRequestParameter(PARAM_NAME_PHONE_NUMBER);
        LOGGER.info(gender);

        Customer customer = new Customer(name, surname, gender, dateOfBirthday, email, phoneNumber);

        if (userService.registration(customer, password) != -1) {
            page = ConfigurationManager.getProperty("path.page.home");
            LOGGER.info("Registration success!");
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
            request.setRequestAttribute("errorRegMessage", MessageManager.getProperty("message.registererror"));
            LOGGER.info("Registration failed!");
        }

        return page;
    }
}
