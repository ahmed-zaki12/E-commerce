import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            System.out.println("Not enough stock for: " + product.getName());
            return;
        }

        items.add(new CartItem(product, quantity));
        System.out.println(quantity + "x " + product.getName() + " added to cart.");
    }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        double subtotal = 0;
        double shipping = 30;
        double totalWeight = 0;
        List<String> shipmentLines = new ArrayList<>();
        List<String> receiptLines = new ArrayList<>();

        // Calculate weights and prepare lines
        for (CartItem item : items) {
            Product product = item.getProduct();
            int qty = item.getQuantity();

            // Check expiration if it's an expirable product
            if (product instanceof ExpirableProduct) {
                ExpirableProduct exp = (ExpirableProduct) product;
                if (exp.isExpired()) {
                    System.out.println("Cannot buy expired product: " + product.getName());
                    return;
                }
            }

            // Check stock again before finalizing
            if (!product.isAvailable(qty)) {
                System.out.println("Insufficient stock for: " + product.getName());
                return;
            }

            // For shipment notice (only shippable products)
            if (product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                double weight = shippable.getWeight() * qty;
                totalWeight += weight;
                String weightStr = weight < 1 ? (int)(weight * 1000) + "g" : String.format("%.1fkg", weight);
                shipmentLines.add(qty + "x " + product.getName() + "    " + weightStr);
            }

            // For receipt
            double lineTotal = product.getPrice() * qty;
            receiptLines.add(qty + "x " + product.getName() + "    " + (int)lineTotal);
            subtotal += lineTotal;
        }

        // If total weight > 30kg, do not ship, do not add shipping fees
        boolean canShip = totalWeight <= 30.0;
        double amount = subtotal + (canShip ? shipping : 0);
        if (customer.getBalance() < amount) {
            System.out.println("Not enough balance to complete the purchase.");
            return;
        }

        // Deduct balance and product quantities
        for (CartItem item : items) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }
        customer.decreaseBalance(amount);

        if (canShip) {
            // Print shipment notice
            System.out.println("** Shipment notice **");
            for (String line : shipmentLines) {
                System.out.println(line);
            }
            String totalWeightStr = totalWeight < 1 ? (int)(totalWeight * 1000) + "g" : String.format("%.1fkg", totalWeight);
            System.out.println("Total package weight " + totalWeightStr);
            System.out.println();
        } else {
            System.out.println("Order too heavy to ship. No shipping will be performed and no shipping fees applied.");
        }

        // Print checkout receipt
        System.out.println("** Checkout receipt **");
        for (String line : receiptLines) {
            System.out.println(line);
        }
        System.out.println("------------------------");
        System.out.println("Subtotal      " + (int)subtotal);
        if (canShip) {
            System.out.println("Shipping      " + (int)shipping);
        }
        System.out.println("Amount        " + (int)amount);

        // Clear the cart
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearCart() {
        items.clear();
    }

    public List<CartItem> getItems() {
        return items;
    }
}
