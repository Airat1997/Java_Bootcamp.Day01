import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        User john = new User("John", 100);
        User mike = new User("Mike", 200);
        Transaction transaction = new Transaction(UUID.randomUUID(), mike, john, true, 100);
        Transaction transaction2 = new Transaction(UUID.randomUUID(), john, mike, false, -100);
        TransactionsLinkedList repositoryTransactionsLinkedList = new TransactionsLinkedList();
        repositoryTransactionsLinkedList.add(transaction);
        repositoryTransactionsLinkedList.add(transaction2);
        System.out.println(
                repositoryTransactionsLinkedList.getById(transaction.getId()).getRecipient()
                        .getName());
        System.out.println(
                repositoryTransactionsLinkedList.getById(transaction2.getId()).getRecipient()
                        .getName());

        Transaction[] array = repositoryTransactionsLinkedList.toArray();
        for (Transaction c : array) {
            System.out.println(c.getRecipient().getName());
        }
        System.out.println("\n");

        repositoryTransactionsLinkedList.remove(transaction.getId());
        Transaction[] array2 = repositoryTransactionsLinkedList.toArray();
        for (Transaction c : array2) {
            System.out.println(c.getRecipient().getName());
        }
        repositoryTransactionsLinkedList.remove(transaction.getId());


    }
}
