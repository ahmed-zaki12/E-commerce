public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public Double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name; // or use getName() if it's private
    }

    @Override
    public void getData() {
        super.getData();
        System.out.println(" - Weight: " + weight + " kg");
    }
}
