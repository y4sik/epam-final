package com.web.securedApp.controller.command;

import com.web.securedApp.controller.servlet.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent request);
}
