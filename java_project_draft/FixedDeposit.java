// FixedDeposit.java

public class FixedDeposit {
    private String username;
    private double amount;

    public FixedDeposit(String username, double amount) {
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
