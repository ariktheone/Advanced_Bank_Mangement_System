import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class WorkerLoginScreen {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton; // New back button
    private BankingSystem bankingSystem;

    public WorkerLoginScreen(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
        frame = new JFrame("Worker Login");
        frame.setSize(300, 200);
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
        loginButton.setBounds(100, 80, 80, 25);
        panel.add(loginButton);

        backButton = new JButton("Back"); // New back button
        backButton.setBounds(190, 80, 80, 25);
        panel.add(backButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            // Verify worker credentials and proceed accordingly
            // Add your logic here
            boolean loginSuccessful = verifyWorkerCredentials(username, password);
            if (loginSuccessful) {
                frame.dispose();
                // Proceed to worker screen
                showWorkerScreen();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            // Go back to the initial screen
            InitialScreen initialScreen = new InitialScreen(bankingSystem);
            initialScreen.showInitialScreen();
        });
    }

    private boolean verifyWorkerCredentials(String username, String password) {
        // Load workers from CSV file
        List<Worker> workers = WorkerCSVManager.loadWorkers("workers.csv");

        // Check if the entered username and password match any worker's credentials
        for (Worker worker : workers) {
            if (worker.getUsername().equals(username) && worker.getPassword().equals(password)) {
                return true; // Credentials are valid
            }
        }

        return false; // No worker found with the entered credentials
    }

    private void showWorkerScreen() {
        // Create and show the worker screen
        WorkerScreen workerScreen = new WorkerScreen(bankingSystem);
        workerScreen.showWorkerScreen();
    }

    public void showWorkerLoginScreen() {
        // Add components and functionality for worker login screen here
        frame.setVisible(true);
    }
}
