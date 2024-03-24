import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialScreen {
    private JFrame frame;
    private JPanel panel;
    private JButton adminLoginButton;
    private JButton userLoginButton;
    private JButton newAccountButton;
    private JButton workerLoginButton;
    private BankingSystem bankingSystem;

    public InitialScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Banking System - Initial Screen");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout()); // Use GridBagLayout for flexible layout
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Make the logo take up the entire width
        gbc.gridheight = 4; // Span the logo across four rows

        // Add logo image
        ImageIcon icon = new ImageIcon("bankimg1.png"); // Load the logo image
        JLabel logoLabel = new JLabel(icon);
        panel.add(logoLabel, gbc);

        // Adjust constraints for the buttons
        gbc.gridx = 1; // Align buttons to the right
        gbc.gridy = 0;
        gbc.gridheight = 1; // Reset grid height
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding around buttons

        // Add admin login button
        adminLoginButton = new JButton("Admin Login");
        panel.add(adminLoginButton, gbc);

        // Add user login button
        gbc.gridy++;
        userLoginButton = new JButton("User Login");
        panel.add(userLoginButton, gbc);

        // Add new account button
        gbc.gridy++;
        newAccountButton = new JButton("New Account");
        panel.add(newAccountButton, gbc);

        // Add worker login button
        gbc.gridy++;
        workerLoginButton = new JButton("Worker Login");
        panel.add(workerLoginButton, gbc);

        // Set action listeners for the buttons
        adminLoginButton.addActionListener(e -> {
            frame.dispose();
            AdminLoginScreen adminLoginScreen = new AdminLoginScreen(bankingSystem);
            adminLoginScreen.showAdminLoginScreen();
        });

        userLoginButton.addActionListener(e -> {
            frame.dispose();
            BankingSystemGUI gui = new BankingSystemGUI(bankingSystem);
            gui.showLoginScreen();
        });

        newAccountButton.addActionListener(e -> {
            frame.dispose();
            NewAccountScreen newAccountScreen = new NewAccountScreen(bankingSystem);
            newAccountScreen.showNewAccountScreen();
        });

        workerLoginButton.addActionListener(e -> {
            frame.dispose();
            WorkerLoginScreen workerLoginScreen = new WorkerLoginScreen(bankingSystem);
            workerLoginScreen.showWorkerLoginScreen();
        });
    }

    public void showInitialScreen() {
        frame.setVisible(true);
    }
}
