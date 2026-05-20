package onlinesurveysystem;

import javax.swing.*;
import java.awt.*;

public class ThankYouPage extends JFrame {

    JLabel thankLabel;

    public ThankYouPage() {

        setTitle("Thank You");

        setSize(550, 300);

        setLayout(null);

        getContentPane().setBackground(new Color(255, 228, 225));

        thankLabel = new JLabel("Thank You For Your Response!");

        thankLabel.setFont(new Font("Verdana", Font.BOLD, 24));

        thankLabel.setForeground(new Color(153, 0, 76));

        thankLabel.setBounds(25, 100, 500, 40);

        add(thankLabel);

        setVisible(true);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}