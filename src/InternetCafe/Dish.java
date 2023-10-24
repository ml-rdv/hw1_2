package InternetCafe;

import java.util.List;

public class Dish {
    private int id;
    private String name;
    private double price;
    private List<Product> products;

    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public boolean isAvailable() {
        return true;
    }

    public void changePrice(double price) {
    }

    public void viewProducts() {
    }
}
