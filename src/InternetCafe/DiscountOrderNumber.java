package InternetCafe;

public class DiscountOrderNumber implements Discount {
    private final int orderNumber; // protected ???

    public DiscountOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public int calculatePercent() {
        return 0;
    }
}
