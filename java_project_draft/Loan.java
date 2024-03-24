// Loan.java

public class Loan {
    private String username;
    private double amount;

    public Loan(String username, double amount) {
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }
}
