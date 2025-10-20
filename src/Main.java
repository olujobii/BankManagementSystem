import app.BankService;

void main(){
    ConsoleUI consoleUI = new ConsoleUI(new Scanner(System.in),new BankService(new ArrayList<>()));

    consoleUI.startApplication();
}