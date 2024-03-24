import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingSystemScreen {
    private JFrame frame;
    private JPanel panel;
    private JLabel nameLabel;
    private JLabel accountNumberLabel;
    private JLabel accountTypeLabel;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton logoutButton;
    private BankingSystem bankingSystem;
    private User currentUser;

    public BankingSystemScreen(BankingSystem bankingSystem, User currentUser) {
        this.bankingSystem = bankingSystem;
        this.currentUser = currentUser;
        frame = new JFrame("Banking System - Welcome " + currentUser.getName());
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        nameLabel = new JLabel("Name: " + currentUser.getName());
        nameLabel.setBounds(10, 20, 300, 25);
        panel.add(nameLabel);

        accountNumberLabel = new JLabel("Account Number: " + currentUser.getAccountNumber());
        accountNumberLabel.setBounds(10, 50, 300, 25);
        panel.add(accountNumberLabel);

        accountTypeLabel = new JLabel("Account Type: " + currentUser.getAccountType());
        accountTypeLabel.setBounds(10, 80, 300, 25);
        panel.add(accountTypeLabel);

        balanceLabel = new JLabel("Balance: $" + currentUser.getBalance());
        balanceLabel.setBounds(10, 110, 300, 25);
        panel.add(balanceLabel);

        JLabel amountLabel = new JLabel("Enter amount:");
        amountLabel.setBounds(10, 140, 120, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(140, 140, 100, 25);
        panel.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(10, 170, 100, 25);
        panel.add(depositButton);

        depositButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            currentUser.setBalance(currentUser.getBalance() + amount);
            updateBalanceLabel();
            bankingSystem.updateUserFile("users.csv");
        });

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(120, 170, 100, 25);
        panel.add(withdrawButton);

        withdrawButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= currentUser.getBalance()) {
                currentUser.setBalance(currentUser.getBalance() - amount);
                updateBalanceLabel();
                bankingSystem.updateUserFile("users.csv");
            } else {
                JOptionPane.showMessageDialog(frame, "Insufficient funds!");
            }
        });

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(230, 170, 100, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener(e -> {
            frame.dispose(); // Close the banking system screen
            new BankingSystemGUI(bankingSystem).showLoginScreen();
        });
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + currentUser.getBalance());
    }
}
