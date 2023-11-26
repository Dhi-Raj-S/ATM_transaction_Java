import java.util.ArrayList;

public class Account {
    private String name; // The name of the account

    private String uuid; // The account ID number

    private User holder; // The user Object that owns this account

    private ArrayList<Transaction> transactions; // The list of transactions for this account

    /**
     * Create a new account
     * @param name  the name of the account
     * @param holder  the user object that holds this account
     * @param theBank  the bank that issues this account
     */

    public Account(String name, User holder, Bank theBank) {

        this.name = name; // set the account name

        this.holder = holder; // set the account holder

        this.uuid = theBank.getNewAccountUUID(); // get new account UUID

        this.transactions = new ArrayList<Transaction>(); // init transaction

    }

    /**
     * Get the account ID
     * @return the account uuid
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Get summary line for the account
     * @return the string summary
     */
    public String getSummaryLine() {

        //get the account's balance
        double balance = this.getBalance();

        // format the summary line depending on whether the balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    /**
     * Get the balance of this account by adding the amount of this transaction
     * @return the balance value
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    /**
     * Print the transaction history of the account
     */
    public void printTransHistory() {
        System.out.printf("Transaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size()-1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in this account
     * @param amount the amount transacted
     * @param memo the transaction memo
     */
    public void addTransaction(double amount, String memo) {

        // Create a new transaction object and add it to out list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }
}
