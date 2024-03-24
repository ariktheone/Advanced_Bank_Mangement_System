import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FDMangementScreen {
    private JFrame frame;
    private JPanel panel;
    private JList<String> fdList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JButton refreshButton;
    private JButton approveFDButton;
    private JButton backButton;
    private BankingSystem bankingSystem;



    public FDMangementScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Fixed Deposit Management");
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
        fdList = new JList<>(listModel);
        scrollPane = new JScrollPane(fdList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);

        refreshButton = new JButton("Refresh");
        buttonPanel.add(refreshButton);

        approveFDButton = new JButton("Approve Fixed Deposit");
        buttonPanel.add(approveFDButton);

        backButton = new JButton("Back"); // New button to go back
        buttonPanel.add(backButton);

        refreshButton.addActionListener(e -> refreshFDList());
        approveFDButton.addActionListener(e -> approveFD());
        backButton.addActionListener(e -> goBack());
        refreshFDList();
    }

    private void refreshFDList() {
        listModel.clear();
        List<FixedDeposit> fds = readFixedDepositsFromCSV();
        for (FixedDeposit fd : fds) {
            listModel.addElement("User: " + fd.getUsername() + ", Amount: $" + fd.getAmount());
        }
    }

    private List<FixedDeposit> readFixedDepositsFromCSV() {
        List<FixedDeposit> fixedDeposits = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("fixed_deposits.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String username = data[0];
                    double amount = Double.parseDouble(data[1]);
                    fixedDeposits.add(new FixedDeposit(username, amount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fixedDeposits;
    }

    private void approveFD() {
        String selectedFD = fdList.getSelectedValue();
        if (selectedFD != null) {
            // Add logic to approve the selected fixed deposit
            JOptionPane.showMessageDialog(frame, "Fixed Deposit approved.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a fixed deposit to approve.");
        }
    }

    private void goBack() {
        // Hide this screen and go back to the previous screen
        frame.dispose();
        // Assuming you have a reference to the previous screen
        // You can show it using the appropriate method
        // bankingSystem.showPreviousScreen();
    }
    public void showFDMangementScreen() {
        frame.setVisible(true);
    }
}
