public class Product {
    protected String name;
    protected Double price;
    protected int quantity;

    Product(){
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
    }
    Product(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void getData(){
        System.out.print(name + " - $" + price + " - " + quantity);
    }
    public void decreaseQuantity(int amount) {
        if(amount <= quantity)
            quantity -= amount;
        else{
            throw new IllegalArgumentException("not enough quantity");
        }
    }
    public boolean isAvailable(int reqQuantity){
        return quantity >= reqQuantity;
    }
}
