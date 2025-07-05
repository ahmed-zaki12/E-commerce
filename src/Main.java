import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create Products (all as ShippableProduct)
        Product scratchCard = new ShippableProduct("Scratch Card", 10.0, 50, 0.01); // 10g
        Product cheese = new ShippableProduct("Cheese", 25.0, 10, 0.2); // 200g
        Product tv = new ShippableProduct("TV", 15000.0, 2, 20.0); // 20kg

        // Step 2: Create Customer
        Customer ahmed = new Customer("Ahmed", 20000.0);

        // Step 3: Add products to Ahmed's cart
        ahmed.getCart().addProduct(scratchCard, 5); // 5x 0.01kg = 0.05kg
        ahmed.getCart().addProduct(cheese, 2);      // 2x 0.2kg = 0.4kg
        ahmed.getCart().addProduct(tv, 1);          // 1x 20kg = 20kg

        // Step 4: Checkout
        ahmed.getCart().checkout(ahmed);
    }
}
