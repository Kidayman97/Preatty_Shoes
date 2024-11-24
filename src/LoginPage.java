import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Login");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a panel for the top section
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(45, 45, 90)); // Background color
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Load the logo image
        ImageIcon logoIcon = new ImageIcon("path/to/logo.png"); // Update with the correct path to your image
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel loginLabel = new JLabel("Log in to your account");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(logoLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(loginLabel);

        // Create a panel for the center section
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        centerPanel.setBackground(new Color(45, 45, 90));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JTextField userField = new JTextField();
        userField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passField = new JPasswordField();
        passField.setBorder(BorderFactory.createTitledBorder("Password"));

        centerPanel.add(userField);
        centerPanel.add(passField);

        // Create a panel for the bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(45, 45, 90));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));

        bottomPanel.add(loginButton);

        // Add panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                User user = DatabaseConnection.checkLogin(username, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login successful!");
                    if ("admin".equalsIgnoreCase(user.getType())) {
                        dispose();
                        new AdminPage();
                    } else {
                        dispose();
                        new UserPage();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Invalid username or password.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
