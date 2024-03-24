import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;



public class BankingSystem {
    private List<User> users;
    private List<Loan> loans;
    private List<FixedDeposit> fixedDeposits;

    public BankingSystem() {
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.fixedDeposits = new ArrayList<>();
    }

    // Methods to add users, loans, and fixed deposits
    public void addUser(User user) {
        users.add(user);
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    public void addFixedDeposit(FixedDeposit fixedDeposit) {
        fixedDeposits.add(fixedDeposit);
    }

    //Methods to retrieve lists of users, loans, and fixed deposits
    public List<User> getUsers() {
       return users;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<FixedDeposit> getFixedDeposits() {
        return fixedDeposits;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    //public List<User> getUsers() {
    //    return users;
    //}

    public void updateUserFile(String filename) {
        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("name,username,password,balance,accountType,accountStatus,accountNumber\n");
            for (User user : users) {
                bw.write(user.getName() + "," + user.getUsername() + "," + user.getPassword() + "," +
                        user.getBalance() + "," + user.getAccountType() + "," +
                        user.getAccountStatus() + "," + user.getAccountNumber() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IOException here
        }
    }



    public void deposit(User user, double amount) {
        user.deposit(amount);
        updateUserFile("users.csv");
    }

    public boolean withdraw(User user, double amount) {
        boolean success = user.withdraw(amount);
        if (success) {
            updateUserFile("users.csv");
        }
        return success;
    }

    // Other banking functionalities (deposit, withdraw, etc.) can be implemented here
}

