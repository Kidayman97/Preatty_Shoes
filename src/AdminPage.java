import java.awt.*;
import java.sql.ResultSet;
import javax.swing.*;

public class AdminPage extends JFrame {
    public AdminPage(ResultSet resultSet) {
        setTitle("PrettyPROD 1.0");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header
        add(createHeader(), BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add panels to main panel
        mainPanel.add(createConfigPanel());
        mainPanel.add(createFichePanel());
        mainPanel.add(createSuiviPanel());

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Logo
        add(createLogoLabel(), BorderLayout.EAST);

        setVisible(true);
    }

    private JLabel createHeader() {
        JLabel headerLabel = new JLabel("PrettyPROD 1.0", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        return headerLabel;
    }

    private JPanel createConfigPanel() {
        JPanel configPanel = createVerticalPanel("Config");
        JButton paramButton = createStyledButton("Paramétrage");
        paramButton.addActionListener(e -> new Parametrage());
        configPanel.add(paramButton);
        return configPanel;
    }

    private JPanel createFichePanel() {
        JPanel fichePanel = createVerticalPanel("Fiche");
        JButton ficheButton = createStyledButton("Fiche technique");
        ficheButton.addActionListener(e -> new Fiche_Technique());
        fichePanel.add(ficheButton);
        return fichePanel;
    }

    private JLabel createLogoLabel() {
        ImageIcon logoIcon = new ImageIcon("path/to/logo.png"); // Update with the correct path
        return new JLabel(logoIcon);
    }

    private JPanel createVerticalPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton("<html><body style='white-space: nowrap;'>" + text + "</body></html>");
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setMargin(new Insets(10, 20, 10, 20));
        button.setFocusPainted(false);
        return button;
    }

    private JPanel createSuiviPanel() {
        JPanel suiviPanel = new JPanel();
        suiviPanel.setLayout(new BoxLayout(suiviPanel, BoxLayout.Y_AXIS));
        suiviPanel.setBorder(BorderFactory.createTitledBorder("Suivi"));

        JPanel updateButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton updateButton = createStyledButton("Mise à jour");
        updateButton.setPreferredSize(new Dimension(180, 60));
        updateButton.setMaximumSize(new Dimension(180, 60));
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.RED);
        updateButtonPanel.add(updateButton);
        updateButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        suiviPanel.add(updateButtonPanel);

        suiviPanel.add(Box.createVerticalStrut(20));

        JPanel rightAlignedPanel = new JPanel();
        rightAlignedPanel.setLayout(new BoxLayout(rightAlignedPanel, BoxLayout.Y_AXIS));
        rightAlignedPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension buttonSize = new Dimension(900, 40);
        String[] buttonLabels = {"MATIERE UTILISE/ARTICLE", "FOURNISSEUR / MATIERE", "CLIENT/ARTICLE"};

        for (String label : buttonLabels) {
            JButton button = createStyledButton(label);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            rightAlignedPanel.add(button);
        }

        suiviPanel.add(rightAlignedPanel);
        suiviPanel.add(Box.createVerticalGlue());

        return suiviPanel;
    }
}