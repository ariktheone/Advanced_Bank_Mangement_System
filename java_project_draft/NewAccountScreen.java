import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewAccountScreen {
    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField balanceField;
    private JComboBox<String> accountTypeComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton createAccountButton;
    private JButton backButton; // New back button
    private BankingSystem bankingSystem;

    public NewAccountScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Banking System - New Account Creation");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(100, 20, 250, 25);
        panel.add(nameField);

        JLabel balanceLabel = new JLabel("Initial Balance:");
        balanceLabel.setBounds(10, 50, 100, 25);
        panel.add(balanceLabel);

        balanceField = new JTextField(20);
        balanceField.setBounds(100, 50, 250, 25);
        panel.add(balanceField);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setBounds(10, 80, 100, 25);
        panel.add(accountTypeLabel);

        String[] accountTypes = {"Savings", "Checking", "Investment"};
        accountTypeComboBox = new JComboBox<>(accountTypes);
        accountTypeComboBox.setBounds(100, 80, 250, 25);
        panel.add(accountTypeComboBox);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(10, 110, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 110, 250, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 140, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 140, 250, 25);
        panel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(10, 170, 150, 25);
        panel.add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBounds(160, 170, 190, 25);
        panel.add(confirmPasswordField);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(10, 200, 150, 25);
        panel.add(createAccountButton);

        backButton = new JButton("Back"); // Add back button
        backButton.setBounds(170, 200, 80, 25);
        panel.add(backButton);

        createAccountButton.addActionListener(e -> {
            String name = nameField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            String accountType = (String) accountTypeComboBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match!");
                return;
            }

            Random rand = new Random();
            String accountNumber = String.valueOf(rand.nextInt(900000) + 100000); // Random 6-digit account number

            // Create a new user and add it to the banking system
            User newUser = new User(name, username, password, balance, accountType, accountNumber);
            bankingSystem.addUser(newUser);
            bankingSystem.updateUserFile("users.csv");

            // Show the account details screen for the new user
            frame.dispose();
            new BankingSystemScreen(bankingSystem, newUser);
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            InitialScreen initialScreen = new InitialScreen(bankingSystem);
            initialScreen.showInitialScreen();
        });
    }

    public void showNewAccountScreen() {
        frame.setVisible(true);
    }
}
