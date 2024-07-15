import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private LinkedList<Transaction> repository;

    public TransactionsLinkedList() {
        this.repository = new LinkedList<>();
    }

    @Override
    public void add(Transaction transaction) {
        repository.add(transaction);
    }

    @Override
    public boolean remove(UUID id) {
        repository.remove(getById(id));
        return true;
    }

    @Override
    public Transaction getById(UUID id) {
        for (Transaction c : repository) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new TransactionNotFoundException("TransactionNotFoundException!");
    }

    @Override
    public Transaction[] toArray() {
        return repository.toArray(repository.toArray(new Transaction[0]));
    }
}
