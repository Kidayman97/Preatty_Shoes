import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Fiche_Technique extends JFrame {

    private DatabaseConnection dbConnection;

    public Fiche_Technique() {
        this.dbConnection = new DatabaseConnection();
        setupFrame();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Fiche_Technique(ResultSet resultSet) {
        this();
        displayAdminData(resultSet);
    }

    private void setupFrame() {
        setTitle("Fiche Technique");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void displayAdminData(ResultSet rs) {
        try {
            if (rs != null) {
                JTable table = new JTable(new ResultSetTableModel(rs));
                JScrollPane scrollPane = new JScrollPane(table);
                add(scrollPane, BorderLayout.CENTER);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving admin data: " + e.getMessage());
        }
    }
}