package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

public class EmptyCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(EmptyCommand.class);

    @Override
    public String execute(SessionRequestContent request) {

        return ConfigurationManager.getProperty("path.page.error");
    }
}
