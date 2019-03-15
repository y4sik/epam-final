package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.MessageManager;
import com.web.securedApp.controller.constant.CommandEnum;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import org.apache.log4j.Logger;

public class ActionFactory {
    private static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(SessionRequestContent request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getRequestParameter("command");
        if (action == null || action.isEmpty()) {
            LOGGER.info("Command is null!");
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setRequestAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
