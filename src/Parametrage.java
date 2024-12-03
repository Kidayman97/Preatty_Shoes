import java.awt.*;
import javax.swing.*;

public class Parametrage extends JFrame {
    public Parametrage() {
        setTitle("Paramétrage");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add tabs
        tabbedPane.addTab("Semelle", new JPanel());
        tabbedPane.addTab("Forme", new JPanel());
        tabbedPane.addTab("Unité", new JPanel());
        tabbedPane.addTab("Phase", new JPanel());
        tabbedPane.addTab("Saison", new JPanel());
        tabbedPane.addTab("Type matiére", new JPanel());
        tabbedPane.addTab("Emplacement", new JPanel());
        tabbedPane.addTab("Famille matiére", new JPanel());
        tabbedPane.addTab("Fournisseur", new JPanel());
        tabbedPane.addTab("Famille", new JPanel());
        tabbedPane.addTab("Couleur", new JPanel());
        tabbedPane.addTab("Matiére", new JPanel());
        tabbedPane.addTab("Article", new JPanel());
        tabbedPane.addTab("Pointure", new JPanel());
        tabbedPane.addTab("Mise", new JPanel());
        tabbedPane.addTab("Mise enfant", new JPanel());


        // Add more tabs as needed

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }
} 