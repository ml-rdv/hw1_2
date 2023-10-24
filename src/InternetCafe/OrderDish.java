package InternetCafe;

public class OrderDish {
    private final Dish dish;
    private final int amount;

    public OrderDish(Dish dish, int amount) {
        this.dish = dish;
        this.amount = amount;
    }

    public Dish getDish() {
        return dish;
    }

    public int getAmount() {
        return amount;
    }
}
