import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingSystem bankingSystem = new BankingSystem();
            UserCSVManager.loadUsers(bankingSystem, "users.csv");

            // Initialize and display the initial screen
            InitialScreen initialScreen = new InitialScreen(bankingSystem);
            initialScreen.showInitialScreen();
        });
    }
}
