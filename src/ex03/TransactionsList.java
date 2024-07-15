import java.util.UUID;

public interface TransactionsList {
    public void add(Transaction transaction);
    public boolean remove(UUID id);

    public Transaction getById(UUID id);

    public Object[] toArray();
}
