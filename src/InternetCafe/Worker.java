package InternetCafe;

import java.util.List;

public class Worker {
    private int id;
    private String fullName;
    private String position;
    private List<Order> orders;

    public Worker(String fullName, String position) {
        this.fullName = fullName;
        this.position = position;
    }

    public boolean changePosition(String position) {
        return true;
    }

    public void handleOrder(Order order) {
    }

    public void editMenu(Menu menu) {
    }

    public void editDish(Dish dish) {
    }

    public void editProduct(Product product) {
    }
}
