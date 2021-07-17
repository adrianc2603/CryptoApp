package code.controller;

import code.view.DisplayOutput;

public class ThreadHelper implements Runnable {

    private UserFacade facade;
    private UserRequest request;
    private String[] container;

    /**
     * Set the UserFacade to use for the thread run - to access the correct API functionality
     * @param f The UserFacade to be set
     */
    public void setUserFacade(UserFacade f) {
        this.facade = f;
    }

    /**
     * Set the UserRequest to use for the thread run - to access the correct API functionality
     * @param req The UserRequest to be set
     */
    public void setRequestType(UserRequest req) {
        this.request = req;
    }

    /**
     * Set the parameter container to use for the thread run - to access the correct API functionality
     * @param con The container containing required parameters for the API functionality
     */
    public void setParameterContainer(String[] con) {
        this.container = con;
    }

    /**
     * Call the UserFacade to access the correct API functionality
     */
    @Override
    public void run() {
        DisplayOutput outputPage = new DisplayOutput();
        if (this.request == UserRequest.LIST) {
            outputPage.displayGivenInfo(facade.getListOfCryptocurrencies(), "List of Cryptocurrencies");
        }
        if (this.request == UserRequest.INFO_NEW) {
            outputPage.displayGivenInfo(facade.getInfoPanelOnCryptoCurrency(container[0], UserRequest.INFO_NEW,
                    false), "Cryptocurrency Info");
        }
        if (this.request == UserRequest.INFO_CACHED) {
            outputPage.displayGivenInfo(facade.getInfoPanelOnCryptoCurrency(container[0], UserRequest.INFO_CACHED,
                    false), "Cryptocurrency Info");
        }
        if (this.request == UserRequest.CONVERT) {
            outputPage.displayGivenInfo(facade.convert(container[0], container[1], container[2]), "Convert");
        }
        if (this.request == UserRequest.REPORT_NEW) {
            outputPage.displayGivenInfo(facade.sendReport(container[0], UserRequest.REPORT_NEW), "Send Report");
        }
        if (this.request == UserRequest.REPORT_CACHED) {
            outputPage.displayGivenInfo(facade.sendReport(container[0], UserRequest.REPORT_CACHED), "Send Report");
        }
    }
}
