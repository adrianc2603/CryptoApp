package code.view;

import code.controller.UserFacade;
import code.controller.UserRequest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

    private final UserFacade facade;

    public HomePage(UserFacade f) {
        this.facade = f;
    }

    /*
     * Please note the below code was previously written by me for Task 3 of this subject
     */

    /**
     * Display the main frame of the application. Shows the buttons to access other functionality
     */
    public void display() {
        JFrame frame = new JFrame("Home Page");
        frame.setBounds(50, 50, 500, 330);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton getList = new JButton("Get List of Cryptocurrencies");
        getList.setBounds(100, 40, 300, 40);
        getList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                facade.createThread(UserRequest.LIST, null);
            }
        });

        JButton getInfo = new JButton("Get Info on a Cryptocurrency");
        getInfo.setBounds(100, 100, 300, 40);
        getInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetInput inputPage = new GetInput(facade);
                inputPage.getCryptocurrencySymbol(new String[1], "Info");
            }
        });

        JButton convert = new JButton("Convert an Amount between Currencies");
        convert.setBounds(100, 160, 300, 40);
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetInput inputPage = new GetInput(facade);
                inputPage.getCurrenciesAndAmount(new String[3]);
            }
        });

        JButton sendReport = new JButton("Send Report to a Phone Number");
        sendReport.setBounds(100, 220, 300, 40);
        sendReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetInput inputPage = new GetInput(facade);
                inputPage.getCryptocurrencySymbol(new String[1], "Report");
            }
        });

        frame.add(getList);
        frame.add(getInfo);
        frame.add(convert);
        frame.add(sendReport);

        frame.setVisible(true);
    }

    /*
     * End of copied code
     */
}
