package code.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayOutput {

    /*
     * Please note the below code was previously written by me for Task 3 of this subject
     */

    /**
     * Display the given info on the screen
     * @param info The given info to be displayed
     * @param title The title of the frame - related to the API request
     */
    public void displayGivenInfo(String info, String title) {
        JFrame frame = new JFrame(title);
        frame.setBounds(450, 50, 950, 490);

        // Add text to screen
        info = info.replaceAll("\n", "<br/>");
        JLabel label = new JLabel("<html>" + info + "</html>");
        label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, label.getFont().getSize()));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        // Allow user to scroll to view all text
        JScrollPane scroll = new JScrollPane(label);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);

        // Add back button
        JButton backButton = new JButton("Click to Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Add back button to bottom of screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /*
     * End of copied code
     */
}
