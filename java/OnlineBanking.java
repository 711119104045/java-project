import java.util.*;

// User class to represent bank users
class User {
    private String username;
    private String password;
    private double balance;
    private List<String> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + username + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

// Bank class to manage users and their accounts
class Bank {
    private Map<String, User> users;

    public Bank() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
}

public class OnlineBanking {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Register users
        bank.registerUser("user1", "password1");
        bank.registerUser("user2", "password2");

        // Login
        User user1 = bank.login("user1", "password1");
        User user2 = bank.login("user2", "password2");

        // Perform transactions
        if (user1 != null) {
            user1.deposit(1000);
            user1.withdraw(500);
            user1.printTransactionHistory();
        }

        if (user2 != null) {
            user2.deposit(1500);
            user2.withdraw(200);
            user2.printTransactionHistory();
        }
    }
}
