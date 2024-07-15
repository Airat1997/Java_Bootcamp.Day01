public class User {

    private int id;
    private String name;

    private double balance;

    private TransactionsList transactionsList = new TransactionsLinkedList();

    public User(String name, double balance) {
        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0.0) {
            System.err.print("Illegal Argument");
            System.exit(-1);
        } else {
            this.balance = balance;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.err.print("Illegal Argument");
            System.exit(-1);
        } else {
            this.balance = balance;
        }
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }
}
