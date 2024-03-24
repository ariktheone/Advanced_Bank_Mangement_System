import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingSystemGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton; // New back button
    private BankingSystem bankingSystem;

    public BankingSystemGUI(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Banking System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        backButton = new JButton("Back"); // Add back button
        backButton.setBounds(100, 80, 80, 25);
        panel.add(backButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Perform authentication logic here
            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                // Open banking system for the user
                openBankingSystem(username);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            InitialScreen initialScreen = new InitialScreen(bankingSystem);
            initialScreen.showInitialScreen();
        });
    }

    private boolean authenticate(String username, String password) {
        User user = bankingSystem.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    private void openBankingSystem(String username) {
        frame.dispose(); // Close the login screen
        User currentUser = bankingSystem.getUserByUsername(username);
        if (currentUser != null) {
            new BankingSystemScreen(bankingSystem, currentUser);
        }
    }

    public void showLoginScreen() {
        frame.setVisible(true);
    }
}
