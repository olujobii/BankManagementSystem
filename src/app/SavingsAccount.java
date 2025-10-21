package app;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount){
        if(balance - amount < 100){
            return false;
        }

        balance -= amount;
        return true;
    }
}
