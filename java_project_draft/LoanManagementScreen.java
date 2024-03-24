import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoanManagementScreen {
    private JFrame frame;
    private JPanel panel;
    private JList<String> loanList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JButton approveLoanButton; // New button for approving loans
    private JButton backButton; // New button to go back to the previous screen
    private BankingSystem bankingSystem;

    public LoanManagementScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Loan Management");
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
        loanList = new JList<>(listModel);
        scrollPane = new JScrollPane(loanList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout()); // New panel for buttons
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        approveLoanButton = new JButton("Approve Loan"); // New button for approving loans
        buttonPanel.add(approveLoanButton);

        backButton = new JButton("Back"); // New button to go back
        buttonPanel.add(backButton);

        refreshButton.addActionListener(e -> refreshLoanList());
        approveLoanButton.addActionListener(e -> approveLoan());
        backButton.addActionListener(e -> goBack());

        refreshLoanList();
    }

    private void refreshLoanList() {
        listModel.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("loans.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String username = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    listModel.addElement("User: " + username + ", Amount: $" + amount);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading loans.");
        }
    }

    private void approveLoan() {
        String selectedLoan = loanList.getSelectedValue();
        if (selectedLoan != null) {
            // Add logic to approve the selected loan
            JOptionPane.showMessageDialog(frame, "Loan approved.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a loan to approve.");
        }
    }

    private void goBack() {
        // Hide this screen and go back to the previous screen
        frame.dispose();
        // Assuming you have a reference to the previous screen
        // You can show it using the appropriate method
        // bankingSystem.showPreviousScreen();
    }

    public void showLoanManagementScreen() {
        frame.setVisible(true);
    }
}
