import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private TransactionsService transactionsService;
    private int currentChoice;
    private final Scanner console;

    public Menu(TransactionsService transactionsService, Scanner console) {
        this.transactionsService = transactionsService;
        this.console = console;
    }

    public void initMenu(boolean profile) {
        while (true) {
            System.out.println("1. Add a user\n"
                    + "2. View user balances\n"
                    + "3. Perform a transfer\n"
                    + "4. View all transactions for a specific user\n"
                    + "5. DEV –remove a transfer by ID\n"
                    + "6. DEV –check transfer validity\n"
                    + "7. Finish execution");
            try {
                currentChoice = console.nextInt();
                choiceFromAction(currentChoice, profile);
            } catch (InputMismatchException e) {
                console.next();
                System.out.println("InputMismatchException");
                System.out.println("Enter correct values");
            }
        }
    }

    private void choiceFromAction(int currentChoice, boolean profile) {
        switch (currentChoice) {
            case 1:
                System.out.println("Enter a user name and a balance");
                addUser();
                break;
            case 2:
                System.out.println("Enter a user ID");
                printUserBalance();
                break;
            case 3:
                System.out.println("Enter a recipient ID, a sender ID, and a transfer amount");
                addTransaction();
                break;
            case 4:
                System.out.println("Enter a user ID");
                allTransaction();
                break;
            case 5:
                if(!profile) return;
                System.out.println("Enter a user ID and a transfer ID");
                removeTransaction();
                break;
            case 6:
                if(!profile) return;
                unPariedTransaction();
                break;
            case 7:
                System.out.println("Finish execution");
                System.exit(0);
        }
    }

    private void addUser() {
        console.nextLine();
        String userInfo = console.nextLine();
        String[] split = userInfo.split(" ");
        if (split.length < 2) {
            System.out.println("Enter name and balance via space");
            return;
        }
        try {
            User user = new User(split[0], Integer.parseInt(split[1]));
            transactionsService.add(user);
            System.out.println("User with id = " + user.getId() + " is added");
            System.out.println("---------------------------------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException");
            System.out.println("Balance must be a number. Enter correct data");
        }

    }

    private void printUserBalance() {
        console.nextLine();
        int id = console.nextInt();
        try {
            System.out.println(
                    transactionsService.getBalance(transactionsService.usersList.getById(id)));
            System.out.println("---------------------------------------------------------");
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException");
            System.out.println("User not found. Enter correct ID.");
        }
    }

    private void addTransaction() {
        console.nextLine();
        String transactionInfo = console.nextLine();
        String[] split = transactionInfo.split(" ");
        try {
            transactionsService.executionTransaction(Integer.parseInt(split[0]),
                    Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            System.out.println("The transfer is completed");
            System.out.println("---------------------------------------------------------");
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException");
            System.out.println("Enter correct values.");
        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException");
            System.out.println("User not found. Enter correct ID.");
        }
    }

    private void allTransaction() {
        console.nextLine();
        try {
            int id = console.nextInt();
            Transaction[] transactions = transactionsService.getTransactions(id);
            for (Transaction c : transactions) {
                System.out.format("To %s(id = %s) %s with id = %s\n", c.getRecipient().getName(),
                        c.getSender().getId(), c.getTransferAmount(), c.getId());
            }
            System.out.println("---------------------------------------------------------");

        } catch (UserNotFoundException e) {
            System.out.println("UserNotFoundException");
            System.out.println("User not found. Enter correct ID.");
        }
    }

    private void removeTransaction() {
        console.nextLine();
        String uuid = console.nextLine();
        String[] strings = uuid.split(" ");
        try {
            int id = Integer.parseInt(strings[0]);
            uuid = strings[1];
            Transaction[] transactions = transactionsService.getTransactions(id);
            Transaction transaction = null;
            for (Transaction c : transactions) {
                if (c.getId().toString().equals(uuid)) {
                    transaction = c;
                    break;
                }
            }
            System.out.println(
                    "Transfer To " + transactionsService.usersList.getById(id).getName() + "(id = "
                            + transaction.getRecipient().getId() + ") "
                            + transaction.getTransferAmount() + " removed");
            transactionsService.remove(transaction.getId(), id);
            System.out.println("---------------------------------------------------------");
        } catch (NumberFormatException e){
            System.out.println("NumberFormatException");
            System.out.println("Enter correct values.");
        } catch (UserNotFoundException e){
            System.out.println("UserNotFoundException");
            System.out.println("User not found. Enter correct ID.");
        } catch (NullPointerException e){
            System.out.println("NullPointerException");
            System.out.println("Transaction not found.");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException");
            System.out.println("Enter correct values.");
        }

    }

    private void unPariedTransaction() {
        Transaction[] unPariedTransactions = transactionsService.unPariedTransactions();
        if(unPariedTransactions.length == 0){
            System.out.println("Not found unpaired transaction.");
            System.out.println("---------------------------------------------------------");
            return;
        }
        System.out.println("Check results: ");
        for (Transaction c : unPariedTransactions) {
            System.out.format("%s(id = %s) has an unacknowledged"
                            + " transfer id = %s from %s(id = %s) for %s\n", c.getRecipient().getName(),
                    c.getRecipient().getId(),
                    c.getId(), c.getSender().getName(), c.getSender().getId(),
                    c.getTransferAmount());
        }
    }
}

