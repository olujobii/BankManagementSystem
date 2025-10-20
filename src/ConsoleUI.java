import app.BankAccount;
import app.BankService;
import app.SavingsAccount;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private BankService bankService;

    public ConsoleUI(Scanner scanner, BankService bankService){
        this.scanner = scanner;
        this.bankService = bankService;
    }

    public void startApplication(){
        boolean isRunning = true;
        System.out.println("==========Welcome to Bank Management System==========");
        String userOption;
        while(isRunning){
            System.out.println("\nPick your choice: ");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Display Accounts");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Check Balance");
            System.out.println("7. Exit");

            userOption = scanner.nextLine().trim();

            switch (userOption){
                case "1":
                    savingsAccount();
                    break;
                case "2":
                    System.out.println("Create Current Account");
                    break;
                case "3":
                    printAccountsList();
                    break;
                case "4":
                    System.out.println("Deposit Money");
                    break;
                case "5":
                    System.out.println("Withdraw Money");
                    break;
                case "6":
                    System.out.println("Check Balance");
                    break;
                case "7":
                    System.out.println("Exit");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    public void savingsAccount(){
        String userName;
        String accountNumber;
        double balance = 0;

        do{
            System.out.print("Enter Account Name: ");
            userName = scanner.nextLine().trim();

            if(bankService.isAccountNameEmpty(userName))
                System.out.println("Invalid account name");
            else
                System.out.println("Account Name: " + userName);
        }while(bankService.isAccountNameEmpty(userName));

        do{
            System.out.print("Enter Account Number: ");
            accountNumber = scanner.nextLine().trim();

            if(bankService.isAccountNumberEmpty(accountNumber))
                System.out.println("Invalid account number");
            else
                System.out.println("Account Number: " + accountNumber);
        }while(bankService.isAccountNumberEmpty(accountNumber));

        do{
            System.out.print("Enter initial deposit amount (Input 0 if you have no deposit to make right now): ");
            if(scanner.hasNextDouble()){
                balance = scanner.nextDouble();
                scanner.nextLine();
            }
            else{
                System.out.println("Invalid deposit amount");
                scanner.nextLine();
                continue;
            }
            if(bankService.isInitialBalanceNegativeOrEmpty(balance))
                System.out.println("Invalid balance");
            else
                System.out.println("Deposit amount: " + balance);
        }while(bankService.isInitialBalanceNegativeOrEmpty(balance));

        if(bankService.isSavingsAccountCreated(userName, accountNumber, balance)){
            System.out.println("Savings Account Created Successfully");
        }
        else{
            System.out.println("There seems to be an error in our system. Please try again later.");
        }
    }

    public void printAccountsList(){
        if(bankService.isBankAccountEmpty()){
            System.out.println("There are no accounts in the system");
        }
        else{
            ArrayList<BankAccount> bankAccounts = bankService.getBankAccounts();
            for(BankAccount bankAccount : bankAccounts){
                System.out.println("\n--------------------");
                System.out.println("Account Name: "+ bankAccount.getAccountName());
                System.out.println("Account Number: "+bankAccount.getAccountNumber());
                System.out.println("Balance: "+bankAccount.getBalance());
                System.out.println("Account Type: "+bankAccount.getClass().getSimpleName());
                System.out.println("--------------------");
            }
        }
    }
}
