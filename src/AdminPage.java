import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome to the Admin Page", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Déconnexion");
        add(logoutButton, BorderLayout.SOUTH);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Déconnecté");
                dispose();
                new LoginPage();
            }
        });

        setVisible(true);
    }
}