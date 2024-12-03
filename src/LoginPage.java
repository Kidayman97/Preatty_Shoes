import entities.User;
import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class LoginPage extends JFrame {
    // Constants for colors and dimensions
    private static final Color BACKGROUND_COLOR = new Color(45, 45, 90);
    private static final Color BUTTON_COLOR = new Color(0, 120, 215);
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int PADDING = 30;

    // UI Components as class fields
    private final JTextField userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();

    public LoginPage() {
        setTitle("Login");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window

        // Top Panel
        JPanel topPanel = createTopPanel();

        // Center Panel
        JPanel centerPanel = createCenterPanel();

        // Bottom Panel
        JPanel bottomPanel = createBottomPanel();

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        JLabel titleLabel = new JLabel("PRETTY SHOES");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(titleLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel loginLabel = new JLabel("Log in to your account");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(loginLabel);

        return topPanel;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        userField.setBorder(BorderFactory.createTitledBorder("Username"));
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        centerPanel.add(userField);
        centerPanel.add(passField);

        return centerPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(BACKGROUND_COLOR);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(BUTTON_COLOR);
        loginButton.setForeground(Color.RED);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));

        loginButton.addActionListener(cae -> handleLogin());

        bottomPanel.add(loginButton);
        return bottomPanel;
    }

    private void handleLogin() {
        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            User user = DatabaseConnection.checkLogin(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();

                if ("admin".equalsIgnoreCase(user.getType())) {
                    ResultSet resultSet = getResultSetForAdminPage();
                    if (resultSet != null) {
                        AdminPage adminPage = new AdminPage(resultSet);
                        adminPage.setVisible(true);
                    } else {
                        System.out.println("Failed to retrieve data for AdminPage.");
                    }
                } else {
                    SwingUtilities.invokeLater(() -> new UserPage());
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                passField.setText("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error connecting to database: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private ResultSet getResultSetForAdminPage() {
        try {
            return DatabaseConnection.getAdminData(); // Ensure this method exists in DatabaseConnection
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginPage());
                }
}