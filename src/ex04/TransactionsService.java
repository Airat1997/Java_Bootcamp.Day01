import java.util.UUID;

public class TransactionsService {

    public UsersList usersList = new UsersArrayList();

    public void add(User user) {
        usersList.add(user);
    }

    public double getBalance(User user) {
        return user.getBalance();
    }

    // true - debit / false - credit
    public void executionTransaction(int recipientId, int senderId, double sum) {
        User recipient = usersList.getById(recipientId);
        User sender = usersList.getById(senderId);

        if (sender.getBalance() < sum) {
            throw new IllegalTransactionException("IllegalTransactionException");
        }

        UUID id = UUID.randomUUID();
        Transaction credit = new Transaction(id, recipient, sender, true, sum);
        Transaction debit = new Transaction(id, sender, recipient, false, -sum);

        sender.getTransactionsList().add(credit);
        recipient.getTransactionsList().add(debit);

        recipient.setBalance(recipient.getBalance() + sum);
        sender.setBalance(sender.getBalance() - sum);
    }

    public Transaction[] getTransactions(int userId) {
        User user = usersList.getById(userId);
        return user.getTransactionsList().toArray();
    }

    public void remove(UUID idTransaction, int userId) {
        User user = usersList.getById(userId);
        user.getTransactionsList().remove(idTransaction);
    }

    public Transaction[] unPariedTransactions() {
        TransactionsList credit = new TransactionsLinkedList();
        TransactionsList debit = new TransactionsLinkedList();
        TransactionsList notPariedTransactions = new TransactionsLinkedList();
        for (int i = 0; i < usersList.length(); i++) {
            User user = usersList.getById(i);
            TransactionsList transactionsList = user.getTransactionsList();
            Transaction[] arrayTransactions = transactionsList.toArray();
            for (Transaction c : arrayTransactions) {
                if (c.isTransferCategory()) {
                    debit.add(c);
                } else {
                    credit.add(c);
                }
            }
        }
        Transaction[] arrayDebit = debit.toArray();
        Transaction[] arrayCredit = credit.toArray();

        for (Transaction d : arrayDebit) {
            boolean isParied = false;
            for (Transaction c : arrayCredit) {
                if (d.getId() == c.getId()) {
                    isParied = true;
                }
            }
            if (!isParied) {
                notPariedTransactions.add(d);
            }
        }
        for (Transaction c : arrayCredit) {
            boolean isParied = false;
            for (Transaction d : arrayDebit) {
                if (c.getId() == d.getId()) {
                    isParied = true;
                }
            }
            if (!isParied) {
                notPariedTransactions.add(c);
            }
        }

        return notPariedTransactions.toArray();
    }

}


