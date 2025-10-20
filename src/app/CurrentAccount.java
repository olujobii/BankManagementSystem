package app;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountName, String accountNo, double balance) {
        super(accountName, accountNo, balance);
    }

    @Override
    public void withdraw(double amount) {
        if(balance - amount < -500){
            System.out.println("Cannot make withdrawal");
            return;
        }
        balance -= amount;
    }
}
