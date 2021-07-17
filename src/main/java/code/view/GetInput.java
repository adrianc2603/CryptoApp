package code.view;

import code.controller.UserFacade;
import code.controller.UserRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetInput {

    private final UserFacade facade;

    public GetInput(UserFacade f) {
        this.facade = f;
    }

    /*
     * Please note the below code was previously written by me for Task 3 of this subject
     */

    /**
     * Get the cryptocurrency symbol from user input
     * @param container Stores the cryptocurrency symbol
     * @param operation Indicates whether the cryptocurrency is used to obtain information or send a report
     */
    public void getCryptocurrencySymbol(String[] container, String operation) {
        final String title;
        if (operation.equals("Report")) {
            title = "Send Report";
        }
        else {
            title = "Get Info";
        }
        JFrame frame = new JFrame(title);
        frame.setBounds(450, 150, 950, 490);

        JLabel label = new JLabel("Enter Cryptocurrency Symbol");
        JButton submitButton = new JButton("Submit");
        JTextField enteredText = new JTextField(20);

        JPanel panel = new JPanel();
        panel.add(enteredText);
        panel.add(submitButton);
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container[0] = enteredText.getText();
                enteredText.setText("Cryptocurrency Submitted");
                panel.remove(submitButton);
            }
        });

        // Add enter button
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayOutput outputPage = new DisplayOutput();
                if (container[0] == null || container[0].isEmpty()) {
                    outputPage.displayGivenInfo("You must enter all parameters", title);
                    enteredText.setText("");
                    panel.add(submitButton);
                }
                else if (facade.isThereCachedInfo(container[0])) {
                    askForNewOrCached(container, operation, title);
                    frame.dispose();
                }
                else {
                    if (operation.equals("Info")) {
                        facade.createThread(UserRequest.INFO_NEW, container);
                    }
                    if (operation.equals("Report")) {
                        facade.createThread(UserRequest.REPORT_NEW, container);
                    }
                    frame.dispose();
                }
            }
        });

        // Add back button
        JButton backButton = new JButton("Click to Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add back and enter buttons to bottom of screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(enterButton, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Get the user to select whether they'd like to use cached cryptocurrency info, or to
     * obtain new cryptocurrency info from the API
     * @param container Stores all wanted fields
     * @param operation operation Indicates whether the cryptocurrency is used to obtain information or send a report
     * @param title The title of the frame - related to the API request
     */
    public void askForNewOrCached(String[] container, String operation, String title) {
        JFrame frame = new JFrame(title);
        frame.setBounds(450, 150, 950, 490);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("<html>Would you like to use New or Cached Cryptocurrency Info?<br/><br/>" +
                "Selecting 'New' Will Also Update the Cached Cryptocurrency Info</html>"));
        frame.add(panel, BorderLayout.CENTER);

        // Add use cached button
        JButton useCached = new JButton("Use Cached Info");
        useCached.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation.equals("Info")) {
                    facade.createThread(UserRequest.INFO_CACHED, container);
                }
                if (operation.equals("Report")) {
                    facade.createThread(UserRequest.REPORT_CACHED, container);
                }
                frame.dispose();
            }
        });

        // Add use new button
        JButton useNew = new JButton("Use New Info");
        useNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (operation.equals("Info")) {
                    facade.createThread(UserRequest.INFO_NEW, container);
                }
                if (operation.equals("Report")) {
                    facade.createThread(UserRequest.REPORT_NEW, container);
                }
                frame.dispose();
            }
        });

        // Add back button
        JButton backButton = new JButton("Click to Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add back and enter buttons to bottom of screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(useCached, BorderLayout.CENTER);
        buttonPanel.add(useNew, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * Get the base currency, target currency, and amount for a conversion operation
     * @param container Stores all wanted fields
     */
    public void getCurrenciesAndAmount(String[] container) {
        JFrame frame = new JFrame("Convert");
        frame.setBounds(450, 250, 950, 490);

        JLabel lP1 = new JLabel("Enter Base Currency");
        JButton submitP1 = new JButton("Submit");
        JTextField enteredP1 = new JTextField(20);

        JPanel p1Panel = new JPanel();
        p1Panel.add(enteredP1);
        p1Panel.add(submitP1);
        p1Panel.add(lP1);

        submitP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container[0] = enteredP1.getText();
                enteredP1.setText("Base Currency Submitted");
                p1Panel.remove(submitP1);
            }
        });

        JLabel lP2 = new JLabel("Enter Currency 2");
        JButton submitP2 = new JButton("Submit");
        JTextField enteredP2 = new JTextField(20);

        JPanel p2Panel = new JPanel();
        p2Panel.add(enteredP2);
        p2Panel.add(submitP2);
        p2Panel.add(lP2);

        submitP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container[1] = enteredP2.getText();
                enteredP2.setText("Currency 2 Submitted");
                p2Panel.remove(submitP2);
            }
        });

        JLabel lP3 = new JLabel("Enter Amount");
        JButton submitP3 = new JButton("Submit");
        JTextField enteredP3 = new JTextField(20);

        JPanel p3Panel = new JPanel();
        p3Panel.add(enteredP3);
        p3Panel.add(submitP3);
        p3Panel.add(lP3);

        submitP3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container[2] = enteredP3.getText();
                enteredP3.setText("Amount Submitted");
                p3Panel.remove(submitP3);
            }
        });

        JPanel panelPanel = new JPanel();
        panelPanel.add(p1Panel, BorderLayout.NORTH);
        panelPanel.add(p2Panel, BorderLayout.CENTER);
        panelPanel.add(p3Panel, BorderLayout.SOUTH);
        frame.add(panelPanel, BorderLayout.CENTER);

        // Add enter button
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean invalidParameter = false;
                if (container[0] == null || container[0].isEmpty()) {
                    invalidParameter = true;
                    enteredP1.setText("");
                    p1Panel.add(submitP1);
                }
                if (container[1] == null || container[1].isEmpty()) {
                    invalidParameter = true;
                    enteredP2.setText("");
                    p2Panel.add(submitP2);
                }
                if (container[2] == null || container[2].isEmpty()) {
                    invalidParameter = true;
                    enteredP3.setText("");
                    p3Panel.add(submitP3);
                }
                if (invalidParameter) {
                    DisplayOutput outputPage = new DisplayOutput();
                    outputPage.displayGivenInfo("You must enter all parameters", "Convert");
                }
                else {
                    facade.createThread(UserRequest.CONVERT, container);
                    frame.dispose();
                }
            }
        });

        // Add back button
        JButton backButton = new JButton("Click to Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add back and enter buttons to bottom of screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, BorderLayout.WEST);
        buttonPanel.add(enterButton, BorderLayout.EAST);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /*
     * End of copied code
     */

}
