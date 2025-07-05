import java.time.LocalDate;
public class ExpirableProduct extends Product {
    private LocalDate startDate;
    private LocalDate expiryDate;

    ExpirableProduct(String name, Double price, int quantity, LocalDate startDate, LocalDate expiryDate) {
        super(name, price, quantity);
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    @Override
    public void getData(){
        super.getData();
        System.out.println(" - StartDate: " + startDate + " - ExpiryDate: " + expiryDate);
    }

    public boolean isExpired(){
        return expiryDate.isBefore(LocalDate.now());
    }
}
