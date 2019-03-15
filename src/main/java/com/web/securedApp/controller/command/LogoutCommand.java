package com.web.securedApp.controller.command;

import com.web.securedApp.controller.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

public class LogoutCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        request.getSession(false).invalidate();
        HomeCommand command = new HomeCommand();
        page = command.execute(request);
        LOGGER.info("Sign Out!");
        return page;
    }
}
