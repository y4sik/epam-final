package com.web.securedApp.controller.constant;

import com.web.securedApp.controller.command.*;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    HOME {
        {
            this.command = new HomeCommand();
        }
    },
    SHOWPRODUCT {
        {
            this.command = new ProductCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    CATEGORY {
        {
            this.command = new HomeByCategoryCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
