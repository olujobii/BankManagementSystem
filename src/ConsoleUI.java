import app.BankAccount;
import app.BankService;

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
                    createSavingsAccount();
                    break;
                case "2":
                    createCurrentAccount();
                    break;
                case "3":
                    printAccountsList();
                    break;
                case "4":
                    depositMoney();
                    break;
                case "5":
                    withdrawMoney();
                    break;
                case "6":
                    checkBalance();
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

    private void createSavingsAccount(){
        String userName;
        String accountNumber;
        double balance = 0;
        BankAccount uniqueBankAccount;
        boolean isAccountUnique = false;
        do{
            System.out.print("Enter Account Name: ");
            userName = scanner.nextLine().trim();

            if(bankService.isAccountNameOrAccountNumberEmpty(userName))
                System.out.println("Invalid account name");
            else
                System.out.println("Account Name: " + userName);
        }while(bankService.isAccountNameOrAccountNumberEmpty(userName));

        do{
            System.out.print("Enter unique Account Number: ");
            accountNumber = scanner.nextLine().trim();

            if(bankService.isAccountNameOrAccountNumberEmpty(accountNumber)) {
                System.out.println("Invalid account number");
                continue;
            }

            //Checking if Bank account is unique
            uniqueBankAccount = bankService.isAccountAvailable(accountNumber);

            if(uniqueBankAccount != null) {
                System.out.println("Account number exists");
            }
            else {
                System.out.println("Account Number: " + accountNumber);
                isAccountUnique = true;
            }
        }while(!isAccountUnique);

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
            if(bankService.isDepositNegativeOrEmpty(balance))
                System.out.println("Invalid balance");
            else
                System.out.println("Deposit amount: " + balance);
        }while(bankService.isDepositNegativeOrEmpty(balance));

        if(bankService.isSavingsAccountCreated(userName, accountNumber, balance)){
            System.out.println("Savings Account Created Successfully");
        }
        else{
            System.out.println("There seems to be an error in our system. Please try again later.");
        }
    }

    private void createCurrentAccount(){
        String userName;
        String accountNumber;
        double balance = 0;
        BankAccount userBankAccount;
        boolean isAccountUnique = false;
        do{
            System.out.print("Enter Account Name: ");
            userName = scanner.nextLine().trim();

            if(bankService.isAccountNameOrAccountNumberEmpty(userName))
                System.out.println("Invalid account name");
            else
                System.out.println("Account Name: " + userName);
        }while(bankService.isAccountNameOrAccountNumberEmpty(userName));

        do{
            System.out.print("Enter unique Account Number: ");
            accountNumber = scanner.nextLine().trim();

            if(bankService.isAccountNameOrAccountNumberEmpty(accountNumber)) {
                System.out.println("Invalid account number");
                continue;
            }

            //Checking if Bank Account is unique
            userBankAccount = bankService.isAccountAvailable(accountNumber);

            if(userBankAccount != null){
                System.out.println("Bank Account exists");
            }
            else{
                System.out.println("Account Number: "+accountNumber);
                isAccountUnique = true;
            }
        }while(!isAccountUnique);

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
            if(bankService.isDepositNegativeOrEmpty(balance))
                System.out.println("Invalid balance");
            else
                System.out.println("Deposit amount: " + balance);
        }while(bankService.isDepositNegativeOrEmpty(balance));

        if(bankService.isCurrentAccountCreated(userName, accountNumber, balance)){
            System.out.println("Current Account Created Successfully");
        }
        else{
            System.out.println("There seems to be an error in our system. Please try again later.");
        }
    }

    private void printAccountsList(){
        if(bankService.isBankAccountEmpty()){
            System.out.println("There are no accounts in the system");
        }
        else{
            ArrayList<BankAccount> bankAccounts = bankService.getBankAccounts();
            System.out.printf("We have %d account(s) in the system\n", bankAccounts.size());
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

    private void depositMoney(){
        if(bankService.isBankAccountEmpty()){
            System.out.println("There are no accounts in the system");
            return;
        }
        String userAccountNumber;
        double depositAmount = 0;
        BankAccount userBankAccount;

        System.out.print("Enter Account Number: ");
        userAccountNumber = scanner.nextLine();

        //Checks if bank account exists
        userBankAccount = bankService.isAccountAvailable(userAccountNumber);

        if(userBankAccount == null){
            System.out.println("Bank Account does not exist in the system");
            return;
        }

        do{
            System.out.print("Enter deposit amount: ");
            if(!scanner.hasNextDouble()){
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }
            depositAmount = scanner.nextDouble();
            scanner.nextLine();

            if(depositAmount <= 0)
                System.out.println("deposit amount cannot be less than or equal to 0");
        }while(depositAmount <= 0);

        userBankAccount.deposit(depositAmount);
        System.out.println("Deposit successful");
        System.out.println("New balance: "+userBankAccount.getBalance());
    }

    private void withdrawMoney(){
        if(bankService.isBankAccountEmpty()){
            System.out.println("There are no accounts in the system");
            return;
        }

        String userAccountNumber;
        BankAccount userBankAccount;
        double withdrawAmount = 0;
        System.out.print("Enter Account Number: ");
        userAccountNumber = scanner.nextLine();

        //Checks if bank account exists
       userBankAccount = bankService.isAccountAvailable(userAccountNumber);

        if(userBankAccount == null){
            System.out.println("Bank Account does not exist in the system");
            return;
        }

        do{
            System.out.print("Enter withdraw amount: ");
            if(!scanner.hasNextDouble()){
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }
            withdrawAmount = scanner.nextDouble();
            scanner.nextLine();

            if(withdrawAmount <= 0)
                System.out.println("Withdrawal amount cannot be less than or equal to 0");
        }while(withdrawAmount <= 0);

        if(userBankAccount.withdraw(withdrawAmount)){
            System.out.println("Withdrawal of "+withdrawAmount+" is successful.");
            System.out.println("New balance: "+userBankAccount.getBalance());
        }else{
            System.out.println("Withdrawal cannot be processed");
        }
    }

    private void checkBalance(){
        if(bankService.isBankAccountEmpty()){
            System.out.println("There are no accounts in the system");
            return;
        }
        String userAccountNumber;
        BankAccount userBankAccount;
        System.out.print("Enter Bank Account: ");
        userAccountNumber = scanner.nextLine();

        //Check if bank account exists
        userBankAccount = bankService.isAccountAvailable(userAccountNumber);

        if(userBankAccount == null) {
            System.out.println("Bank Account is not available in the system");
            return;
        }

        System.out.println("Account Name: "+userBankAccount.getAccountName());
        System.out.println("Account Number: "+userBankAccount.getAccountNumber());
        System.out.println("Balance: "+userBankAccount.getBalance());
    }

    public void exitApplication(){
        System.out.println("Thank you for using our Bank application, we hope to see you soon.");
        scanner.close();
    }
}
