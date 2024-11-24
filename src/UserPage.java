import javax.swing.*;

public class UserPage extends JFrame {
    public UserPage() {
        setTitle("User Page");
        setSize(600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome to the User Page", SwingConstants.CENTER);
        add(label);
        setVisible(true);
    }
}