import java.util.List;

public class ShippingService {

    // A static method to handle shipping
    public static void shipAll(List<Shippable> items) {
        for (Shippable item : items) {
            if (item.getWeight() <= 30.0) {
                System.out.println("Shipping " + item.getName() + " (" + item.getWeight() + " kg)...");
            } else {
                System.out.println(item.getName() + " is too heavy to ship.");
            }
        }
    }
}
