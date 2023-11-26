import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;


    /**
     * Create a new Bank object with empty lists of users and accounts
     * @param name the name of the Bank
     */
    public Bank(String name) {

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

    public String getNewUserUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // Continue to loop untill we get unique number
        do {
            //Generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }

    /**
     * Generate UUID for an account
     * @return UUId for a new account
     */
    public String getNewAccountUUID() {

        // inits
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // Continue to loop untill we get unique number
        do {
            //Generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid += ((Integer) rng.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    /**
     * Add an account
     * @param anAcct  the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user for the bank
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's pin
     * @return the new user object
     */

    public User addUser(String firstName, String lastName, String pin) {

        // Create a newUser object and add it to our arraylist
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // Create a saving account for the user
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the User object associated with a particular userID and pin, if they are valid
     * @param userID The uuid of the user logging in
     * @param pin The pin of the user
     * @return The User object if the login is successful else return null
     */

    public User userLogin(String userID, String pin) {

        // search through the list of users
        for (User u : this.users) {

            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // If we haven't found the user or have an incorrect pin or uuid
        return null;
    }

    public String getName() {
        return this.name;
    }
}
