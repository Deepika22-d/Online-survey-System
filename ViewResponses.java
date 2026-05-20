package onlinesurveysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewResponses extends JFrame {

    JTable table;

    DefaultTableModel model;

    public ViewResponses() {

        setTitle("Survey Responses");

        setSize(700, 400);

        setLayout(new BorderLayout());

        model = new DefaultTableModel();

        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Rating");
        model.addColumn("Feedback");
        model.addColumn("Time");

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from responses");

            while(rs.next()) {

                model.addRow(new Object[] {

                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getInt("rating"),
                    rs.getString("feedback"),
                    rs.getTimestamp("submitted_time")
                });
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);

        setLocationRelativeTo(null);
    }
}