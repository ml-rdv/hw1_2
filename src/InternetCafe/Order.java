package InternetCafe;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private OrderStatus status;
    private Date registrationDate;
    private double price;
    private Client client;
    private List<OrderDish> dishes;
    private Discount discount;
    private DeliveryMethod deliveryMethod;
    private Check check;

    public Order(Client client, Discount discount) {
        this.client = client;
        this.discount = discount;
    }

    public boolean cancel() {
        return true;
    }

    public boolean confirm() {
        return true;
    }

    public boolean close() {
        return true;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public Check getCheck() {
        return check;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    private void changeStatus(OrderStatus status) {
    }

    private void calculatePrice() {
    }

    public void addDish(Dish dish, int amount) {
    }
}
