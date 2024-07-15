import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();
        User john = new User("John", 100);
        User mike = new User("Mike", 200);
        transactionsService.add(john);
        transactionsService.add(mike);
        System.out.println(transactionsService.getBalance(john));
        transactionsService.executionTransaction(john.getId(), mike.getId(), 20);
        transactionsService.executionTransaction(john.getId(), mike.getId(), 20);
        transactionsService.executionTransaction(john.getId(), mike.getId(), 40);
        System.out.println(transactionsService.getBalance(john));
        System.out.println(transactionsService.getBalance(mike));
        System.out.println("All trans:");
        Transaction[] transactions = transactionsService.getTransactions(mike.getId());
        for (Transaction c: transactions){
            System.out.println(c.getId());
        }

        System.out.println("All trans after remove:");
        transactionsService.remove(transactions[0].getId(), john.getId());
        Transaction[] transactions2 = transactionsService.getTransactions(john.getId());
        for (Transaction c: transactions2){
            System.out.println(c.getId());
        }

        System.out.println("All trans unParied:");
        Transaction[] unParied = transactionsService.unPariedTransactions();
        for (Transaction c: unParied){
            System.out.println(c.getId());
        }


    }
}
