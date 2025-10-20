package app;

public abstract class BankAccount {
    protected String AccountName;
    protected String AccountNumber;
    protected double balance;

    protected BankAccount(String AccountName, String AccountNumber, double balance) {
        this.AccountName = AccountName;
        this.AccountNumber = AccountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountName() {
        return AccountName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }
}
