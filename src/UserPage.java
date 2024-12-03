import javax.swing.*;

public class UserPage extends JFrame {
    public UserPage() {
        setTitle("User Page");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to the User Page", SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}