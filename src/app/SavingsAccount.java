package app;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountName, String accountNumber, double balance) {
        super(accountName, accountNumber, balance);
    }

    @Override
    public void withdraw(double amount){
        if(balance - amount < 100){
            System.out.println("Cannot make withdrawal");
            return;
        }

        balance -= amount;
    }
}
