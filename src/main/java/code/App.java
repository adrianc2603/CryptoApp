package code;

import code.model.api.*;
import code.model.database.Database;
import code.model.database.DatabaseImp;
import code.controller.UserFacade;
import code.controller.UserFacadeImp;
import code.controller.ThreadHelper;
import code.view.LaunchApp;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException("Incorrect number of arguments given");
        }

        InputAPI inputAPI;
        OutputAPI outputAPI;

        if (args[0].equals("online")) {
            inputAPI = new OnlineInput();
        }
        else if (args[0].equals("offline")) {
            inputAPI = new OfflineInput();
        }
        else {
            throw new RuntimeException("Input API must be online or offline");
        }

        if (args[1].equals("online")) {
            outputAPI = new OnlineOutput();
        }
        else if (args[1].equals("offline")) {
            outputAPI = new OfflineOutput();
        }
        else {
            throw new RuntimeException("Output API must be online or offline");
        }

        Database db = new DatabaseImp("src/main/resources/database.db");
        ThreadHelper helper = new ThreadHelper();

        UserFacade facade = new UserFacadeImp(inputAPI, outputAPI, db, helper);

        LaunchApp appLauncher = new LaunchApp(facade);
        appLauncher.getBalance(new String[1]);
    }
}