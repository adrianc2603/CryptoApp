package code.view;

import code.controller.UserFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchApp {

    private final UserFacade facade;

    public LaunchApp(UserFacade f) {
        this.facade = f;
    }

    /*
     * Please note the below code was previously written by me for Task 3 of this subject
     */

    /**
     * Get the balance from user input
     * @param container Stores the balance entered
     */
    public void getBalance(String[] container) {
        JFrame frame = new JFrame("Get Balance");
        frame.setBounds(450, 150, 950, 490);

        JLabel label = new JLabel("Enter Balance");
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
                enteredText.setText("Balance Submitted");
                panel.remove(submitButton);
            }
        });

        // Add enter button
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayOutput outputPage = new DisplayOutput();
                String result = facade.setBalance(container[0]);
                outputPage.displayGivenInfo(result, "Get Balance");
                if (result.equals("Balance has been set to " + container[0])) {
                    HomePage homePage = new HomePage(facade);
                    homePage.display();
                    frame.dispose();
                }
                else {
                    enteredText.setText("");
                    panel.add(submitButton);
                }
            }
        });

        // Add back and enter buttons to bottom of screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(enterButton, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /*
     * End of copied code
     */
}
