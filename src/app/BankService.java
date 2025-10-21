package app;
import java.util.ArrayList;

public class BankService {
    private ArrayList<BankAccount> bankAccounts;

    public BankService(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
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

    public boolean isCurrentAccountCreated(String accountName, String accountNumber, double balance){
        if((accountName == null || accountName.isEmpty()) && (accountNumber == null || accountNumber.isEmpty()) && balance < 0){
            return false;
        }
        BankAccount bankAccount = new CurrentAccount(accountName, accountNumber, balance);
        bankAccounts.add(bankAccount);
        return true;
    }

    public boolean isAccountNameOrAccountNumberEmpty(String accountName){
        return accountName == null || accountName.isEmpty();
    }

    public boolean isDepositNegativeOrEmpty(double initialBalance){
        return initialBalance < 0;
    }

    public BankAccount isAccountAvailable(String userAccountNumber){
        for(BankAccount bankAccount : bankAccounts){
            if(bankAccount.getAccountNumber().equals(userAccountNumber)){
                return bankAccount;
            }
        }
        return null;
    }
}
