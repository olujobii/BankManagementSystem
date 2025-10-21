package app;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountName, String accountNo, double balance) {
        super(accountName, accountNo, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance - amount < -500){
            return false;
        }
        balance -= amount;
        return true;
    }
}
