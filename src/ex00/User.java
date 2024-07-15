import java.util.UUID;

public class User {

    private int id;
    private String name;

    private double balance;

    public User(UUID id, String name, double balance) {
        this.id = id;
        this.name = name;
        if (balance < 0.0) {
            System.err.print("Illegal Argument");
            System.exit(-1);
        } else {
            this.balance = balance;
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

}
