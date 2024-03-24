// UserCSVManager.java
import java.io.*;

public class UserCSVManager {
    public static void loadUsers(BankingSystem bankingSystem, String filename) {
        try (FileReader reader = new FileReader(filename);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            br.readLine(); // skip header line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String username = parts[1];
                String password = parts[2];
                double balance = Double.parseDouble(parts[3]);
                String accountType = parts[4];
                String accountNumber = parts[5];
                User user = new User(name, username, password, balance, accountType, accountNumber);
                bankingSystem.addUser(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
