package app;
import java.util.ArrayList;

public class BankService {
    private ArrayList<BankAccount> bankAccounts;

    public BankService(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }

    public ArrayList<BankAccount> getBankAccounts(){
        return bankAccounts;
    }

    public boolean isBankAccountEmpty(){
        return bankAccounts.isEmpty();
    }

    public boolean isSavingsAccountCreated(String accountName, String accountNumber, double balance){
        if((accountName == null || accountName.isEmpty()) && (accountNumber == null || accountNumber.isEmpty()) && balance < 0){
            return false;
        }
        BankAccount bankAccount = new SavingsAccount(accountName, accountNumber, balance);
        bankAccounts.add(bankAccount);
        return true;
    }
    public boolean isAccountNameEmpty(String accountName){
        return accountName == null || accountName.isEmpty();
    }

    public boolean isAccountNumberEmpty(String accountNumber){
        return accountNumber == null || accountNumber.isEmpty();
    }

    public boolean isInitialBalanceNegativeOrEmpty(double initialBalance){
        return initialBalance < 0;
    }
}
