public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    // ✅ This is the method you need:
    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }

    public void decreaseBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Not enough balance.");
        }
    }

    public String getName() {
        return name;
    }
}
