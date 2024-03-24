import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class AdvancedOptionsScreen {
    private JFrame frame;
    private JPanel panel;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JButton traceUserButton;
    private JButton addFixedDepositButton;
    private JButton applyForLoanButton;
    private JButton showApprovedFDBtn;
    private JButton showApprovedLoansBtn;
    private JButton loanManagementBtn;
    private JButton fdManagementBtn;
    private JButton backButton; // New button for going back
    private BankingSystem bankingSystem;
    private List<User> users;

    public AdvancedOptionsScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Advanced Options");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        scrollPane = new JScrollPane(userList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2)); // New layout for button panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        traceUserButton = new JButton("Trace User");
        buttonPanel.add(traceUserButton);

        addFixedDepositButton = new JButton("Add Fixed Deposit");
        buttonPanel.add(addFixedDepositButton);

        applyForLoanButton = new JButton("Apply for Loan");
        buttonPanel.add(applyForLoanButton);

        showApprovedFDBtn = new JButton("Show Approved Fixed Deposits");
        buttonPanel.add(showApprovedFDBtn);

        showApprovedLoansBtn = new JButton("Show Approved Loans");
        buttonPanel.add(showApprovedLoansBtn);

        loanManagementBtn = new JButton("Loan Management");
        buttonPanel.add(loanManagementBtn);

        fdManagementBtn = new JButton("FD Management");
        buttonPanel.add(fdManagementBtn);

        backButton = new JButton("Back"); // New button for going back
        buttonPanel.add(backButton); // Adding the "Back" button to the panel

        // Add action listeners for the buttons
        refreshButton.addActionListener(e -> refreshUserAccounts());
        traceUserButton.addActionListener(e -> traceUser());
        addFixedDepositButton.addActionListener(e -> addFixedDeposit());
        applyForLoanButton.addActionListener(e -> applyForLoan());
        showApprovedFDBtn.addActionListener(e -> showApprovedFixedDeposits());
        showApprovedLoansBtn.addActionListener(e -> showApprovedLoans());
        loanManagementBtn.addActionListener(e -> openLoanManagementScreen());
        fdManagementBtn.addActionListener(e -> openFDManagementScreen());
        backButton.addActionListener(e -> frame.dispose()); // Close the current window to go back

        refreshUserAccounts();
    }

    private void refreshUserAccounts() {
        listModel.clear();
        users = bankingSystem.getUsers();
        for (User user : users) {
            listModel.addElement("Username: " + user.getUsername() + ", Account Number: " +
                    user.getAccountNumber() + ", Account Type: " + user.getAccountType() +
                    ", Balance: " + user.getBalance());
        }
    }

    private void traceUser() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            JOptionPane.showMessageDialog(frame, "Tracing user '" + username + "'.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to trace.");
        }
    }

    private void addFixedDeposit() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            String input = JOptionPane.showInputDialog(frame, "Enter the amount for fixed deposit for user '" + username + "':");
            try {
                double depositAmount = Double.parseDouble(input);
                User user = bankingSystem.getUserByUsername(username);
                if (user != null) {
                    user.setBalance(user.getBalance() + depositAmount);
                    updateFixedDepositCSV(username, depositAmount);
                    bankingSystem.updateUserFile("users.csv");
                    refreshUserAccounts();
                    JOptionPane.showMessageDialog(frame, "Fixed deposit of $" + depositAmount + " has been added to the account of user '" + username + "' successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid amount.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to add fixed deposit to.");
        }
    }

    private void updateFixedDepositCSV(String username, double depositAmount) {
        try {
            FileWriter writer = new FileWriter("fixed_deposits.csv", true);
            writer.append(username + "," + depositAmount + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyForLoan() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            String input = JOptionPane.showInputDialog(frame, "Enter the amount for loan for user '" + username + "':");
            try {
                double loanAmount = Double.parseDouble(input);
                User user = bankingSystem.getUserByUsername(username);
                if (user != null) {
                    user.setBalance(user.getBalance() + loanAmount);
                    updateLoanCSV(username, loanAmount);
                    bankingSystem.updateUserFile("users.csv");
                    refreshUserAccounts();
                    JOptionPane.showMessageDialog(frame, "Loan of $" + loanAmount + " has been added to the account of user '" + username + "' successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid amount.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to apply for loan.");
        }
    }

    private void updateLoanCSV(String username, double loanAmount) {
        try {
            FileWriter writer = new FileWriter("loans.csv", true);
            writer.append(username + "," + loanAmount + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showApprovedFixedDeposits() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("fixed_deposits.csv"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(frame, "Approved Fixed Deposits:\n" + sb.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showApprovedLoans() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("loans.csv"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            JOptionPane.showMessageDialog(frame, "Approved Loans:\n" + sb.toString());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openLoanManagementScreen() {
        LoanManagementScreen loanManagementScreen = new LoanManagementScreen(bankingSystem);
        loanManagementScreen.showLoanManagementScreen();
    }

    private void openFDManagementScreen() {
        FDMangementScreen fdMangementScreen = new FDMangementScreen(bankingSystem);
        fdMangementScreen.showFDMangementScreen();
    }

    public void showAdvancedOptions() {
        frame.setVisible(true);
    }
}
