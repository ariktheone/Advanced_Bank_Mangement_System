import java.util.Random;

public class User {
    private String name;
    private String username;
    private String password;
    private double balance;
    private String accountType;
    private String accountStatus;
    private String accountNumber;

    public User(String name, String username, String password, double balance, String accountType, String accountStatus) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.accountNumber = generateAccountNumber(); // Automatically generate account number
    }

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true; // Withdraw successful
        } else {
            return false; // Insufficient funds
        }
    }

    // Method to generate a random 10-digit account number
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
