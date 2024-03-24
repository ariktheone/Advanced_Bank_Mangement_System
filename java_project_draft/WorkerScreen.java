import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WorkerScreen {
    private JFrame frame;
    private JPanel panel;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JButton addInterestButton;
    private JButton modifyBalanceButton;
    private JButton searchButton;
    private JButton advancedOptionsButton;
    private JButton logoutButton; // New button for logout
    private JTextField searchField;

    private BankingSystem bankingSystem;

    public WorkerScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Worker Panel");
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

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3)); // New layout for button panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        addInterestButton = new JButton("Add Interest");
        buttonPanel.add(addInterestButton);

        modifyBalanceButton = new JButton("Modify Balance");
        buttonPanel.add(modifyBalanceButton);

        searchField = new JTextField(20); // Adjusted size to match grid layout
        buttonPanel.add(searchField);

        searchButton = new JButton("Search");
        buttonPanel.add(searchButton);

        advancedOptionsButton = new JButton("Advanced Options");
        buttonPanel.add(advancedOptionsButton);

        logoutButton = new JButton("Logout"); // New button for logout
        panel.add(logoutButton, BorderLayout.NORTH);

        refreshButton.addActionListener(e -> refreshUserAccounts());
        addInterestButton.addActionListener(e -> addInterest());
        modifyBalanceButton.addActionListener(e -> modifyBalance());
        searchButton.addActionListener(e -> searchAccount());
        advancedOptionsButton.addActionListener(e -> showAdvancedOptions());
        logoutButton.addActionListener(e -> logout()); // Logout action

        refreshUserAccounts();
    }

    private void refreshUserAccounts() {
        listModel.clear();
        List<User> users = bankingSystem.getUsers();
        for (User user : users) {
            listModel.addElement("Username: " + user.getUsername() + ", Account Number: " +
                    user.getAccountNumber() + ", Account Type: " + user.getAccountType() +
                    ", Balance: " + user.getBalance());
        }
    }

    private void addInterest() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            String input = JOptionPane.showInputDialog(frame, "Enter the interest rate for user '" + username + "' (as a percentage):");
            try {
                double interestRate = Double.parseDouble(input) / 100.0;

                User user = bankingSystem.getUserByUsername(username);
                if (user != null) {
                    double interest = user.getBalance() * interestRate;
                    user.setBalance(user.getBalance() + interest);

                    bankingSystem.updateUserFile("users.csv");

                    refreshUserAccounts();

                    JOptionPane.showMessageDialog(frame, "Interest has been added to the account of user '" + username + "' successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid interest rate.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to add interest to.");
        }
    }

    private void modifyBalance() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            String input = JOptionPane.showInputDialog(frame, "Enter new balance for user '" + username + "':");
            try {
                double newBalance = Double.parseDouble(input);
                User user = bankingSystem.getUserByUsername(username);
                if (user != null) {
                    user.setBalance(newBalance);
                    bankingSystem.updateUserFile("users.csv");
                    refreshUserAccounts();
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid balance.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a user to modify balance.");
        }
    }

    private void searchAccount() {
        String searchTerm = searchField.getText().trim();
        if (!searchTerm.isEmpty()) {
            listModel.clear();
            List<User> users = bankingSystem.getUsers();
            boolean userFound = false;
            for (User user : users) {
                if (user.getUsername().contains(searchTerm)) {
                    listModel.addElement("Username: " + user.getUsername() + ", Account Number: " +
                            user.getAccountNumber() + ", Account Type: " + user.getAccountType() +
                            ", Balance: " + user.getBalance());
                    userFound = true;
                }
            }
            if (!userFound) {
                JOptionPane.showMessageDialog(frame, "No users found matching the search term.");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please enter a username to search.");
        }
    }

    private void showAdvancedOptions() {
        AdvancedOptionsScreen optionsWindow = new AdvancedOptionsScreen(bankingSystem);
        optionsWindow.showAdvancedOptions();
    }

    private void logout() {
        frame.dispose(); // Close the current frame
        // Perform logout actions here, e.g., go back to the worker login screen
        WorkerLoginScreen workerLoginScreen = new WorkerLoginScreen(bankingSystem); // Pass the bankingSystem instance
        workerLoginScreen.showWorkerLoginScreen();
    }



    public void showWorkerScreen() {
        frame.setVisible(true);
    }
}
