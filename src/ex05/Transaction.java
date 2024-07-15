import java.util.UUID;

public class Transaction {

    private UUID id;
    private User recipient;
    private User sender;
    private boolean transferCategory; // true - debit / false - credit
    private double transferAmount;

    public Transaction(UUID id, User recipient, User sender, boolean transferCategory,
            double transferAmount) {
        this.id = id;
        if (!recipient.equals(sender)) {
            this.recipient = recipient;
            this.sender = sender;
        } else {
            printEr();
        }
        this.transferCategory = transferCategory;
        if (transferCategory) {
            if (transferAmount > 0) {
                this.transferAmount = transferAmount;
            } else {
                printEr();
            }
        } else {
            if (transferAmount < 0) {
                this.transferAmount = transferAmount;
            } else {
                printEr();
            }
        }

    }


    private void printEr() {
        System.err.print("Illegal Argument");
        System.exit(-1);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public boolean isTransferCategory() {
        return transferCategory;
    }

    public void setTransferCategory(boolean transferCategory) {
        this.transferCategory = transferCategory;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

}
