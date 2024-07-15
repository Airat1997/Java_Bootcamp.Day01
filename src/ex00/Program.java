import java.util.UUID;

// true - debit / false - credit
public class Program {

    public static void main(String[] args) {
        User john = new User(UUID.randomUUID(), "John", 100);
        User mike = new User(UUID.randomUUID(), "Mike", 200);
        Transaction transaction = new Transaction(UUID.randomUUID(), mike, john, true, 100);
        System.out.format("User info ->uuid %s, name: %s, balance: %s\n",john.getId(), john.getName(), john.getBalance());
        System.out.format(
                "Transaction info ->uuid %s, name user recipient: %s, name user sender: %s, transfer category: %b, transfer amount: %s",
                transaction.getId(),
                transaction.getRecipient().getName(),
                transaction.getSender().getName(), transaction.isTransferCategory(), transaction.getTransferAmount());

    }
}
