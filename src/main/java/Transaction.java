import java.util.Date;

public class Transaction {
    private double amount; // The amount of this transaction

    private Date timestamp; // The date and time of this transaction

    private String memo; // A memo for this transaction

    private Account inAccount; // The account in which the transaction was performed


    /**
     * Create a new Transaction
     * @param amount the amount transacted
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }

    /**
     * Create a new Transaction
     * @param amount the amount transacted
     * @param memo the memo of the transaction
     * @param inAccount the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount) {

        // call the two-arg constructor first
        this(amount, inAccount);

        // set the memo
        this.memo = memo;

    }

    /**
     * Get the amount of this transaction
     * @return the amount
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Get a string summarizing the transactions
     * @return the summary string
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
