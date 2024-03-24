import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminScreen {
    private JFrame frame;
    private JPanel panel;
    private JList<String> userList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JButton modifyBalanceButton;
    private JButton searchButton; // New search button
    private JTextField searchField; // New text field for search
    private JButton backButton;
    private BankingSystem bankingSystem;

    public AdminScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Admin Panel");
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

        JPanel buttonPanel = new JPanel(new FlowLayout()); // New panel for buttons
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        modifyBalanceButton = new JButton("Modify Balance");
        buttonPanel.add(modifyBalanceButton);

        searchField = new JTextField(20); // New text field for search
        buttonPanel.add(searchField);

        searchButton = new JButton("Search"); // New search button
        buttonPanel.add(searchButton);

        backButton = new JButton("Back");
        buttonPanel.add(backButton);

        refreshButton.addActionListener(e -> refreshUserAccounts());
        modifyBalanceButton.addActionListener(e -> modifyBalance());
        searchButton.addActionListener(e -> searchAccount()); // Call searchAccount() on button click
        backButton.addActionListener(e -> {
            frame.dispose();
            InitialScreen initialScreen = new InitialScreen(bankingSystem);
            initialScreen.showInitialScreen();
        });

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

    private void modifyBalance() {
        String selectedUser = userList.getSelectedValue();
        if (selectedUser != null) {
            String username = selectedUser.split(",")[0].split(":")[1].trim();
            String accountNumber = selectedUser.split(",")[1].split(":")[1].trim();
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
}
