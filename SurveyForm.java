package onlinesurveysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SurveyForm extends JFrame implements ActionListener {

    JLabel title, nameLabel, rateLabel, feedbackLabel;

    JTextField nameField;

    JSlider ratingSlider;

    JTextArea feedbackArea;

    JButton submitButton, clearButton, viewButton;

    public SurveyForm() {

        setTitle("ONLINE SURVEY SYSTEM");

        setSize(650, 550);

        setLayout(null);

        getContentPane().setBackground(new Color(230, 240, 255));

        // TITLE
        title = new JLabel("ONLINE SURVEY SYSTEM");
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        title.setForeground(new Color(128, 0, 128));
        title.setBounds(120, 20, 450, 40);

        // NAME
        nameLabel = new JLabel("Enter Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(0, 102, 204));
        nameLabel.setBounds(50, 100, 180, 30);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBounds(260, 100, 250, 35);

        // RATE
        rateLabel = new JLabel("Rate Us:");
        rateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rateLabel.setForeground(new Color(0, 102, 204));
        rateLabel.setBounds(50, 180, 180, 30);

        ratingSlider = new JSlider(0,5,3);
        ratingSlider.setBounds(260,170,250,60);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setBackground(new Color(230, 240, 255));

        // FEEDBACK
        feedbackLabel = new JLabel("Feedback:");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 20));
        feedbackLabel.setForeground(new Color(0, 102, 204));
        feedbackLabel.setBounds(50, 280, 180, 30);

        feedbackArea = new JTextArea();

        JScrollPane scroll = new JScrollPane(feedbackArea);
        scroll.setBounds(260, 260, 250, 100);

        // SUBMIT BUTTON
        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        submitButton.setBackground(new Color(102, 0, 204));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(80, 430, 140, 45);

        // CLEAR BUTTON
        clearButton = new JButton("CLEAR");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.setBackground(Color.RED);
        clearButton.setForeground(Color.WHITE);
        clearButton.setBounds(250, 430, 140, 45);

        // VIEW BUTTON
        viewButton = new JButton("VIEW RESPONSES");
        viewButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewButton.setBackground(new Color(0, 153, 76));
        viewButton.setForeground(Color.WHITE);
        viewButton.setBounds(420, 430, 180, 45);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        viewButton.addActionListener(this);

        // ADD
        add(title);
        add(nameLabel);
        add(nameField);
        add(rateLabel);
        add(ratingSlider);
        add(feedbackLabel);
        add(scroll);

        add(submitButton);
        add(clearButton);
        add(viewButton);

        setVisible(true);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        // SUBMIT
        if(e.getSource() == submitButton) {

            String name = nameField.getText();

            int rating = ratingSlider.getValue();

            String feedback = feedbackArea.getText();

            if(name.isEmpty() || feedback.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                "Please Fill All Fields");

            } else {

                try {

                    Connection con = DBConnection.getConnection();

                    PreparedStatement ps = con.prepareStatement(
                    "insert into responses(username,rating,feedback) values(?,?,?)");

                    ps.setString(1, name);

                    ps.setInt(2, rating);

                    ps.setString(3, feedback);

                    int rows = ps.executeUpdate();

                    if(rows > 0) {

                        new ThankYouPage();

                        dispose();
                    }

                    ps.close();
                    con.close();

                } catch(Exception ex) {

                    ex.printStackTrace();
                }
            }
        }

        // CLEAR BUTTON
        if(e.getSource() == clearButton) {

            nameField.setText("");

            feedbackArea.setText("");

            ratingSlider.setValue(3);
        }

        // VIEW RESPONSES
        if(e.getSource() == viewButton) {

            new ViewResponses();
        }
    }

    public static void main(String args[]) {

        new SurveyForm();
    }
}