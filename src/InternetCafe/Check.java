package InternetCafe;

import java.util.Date;

public class Check {
    private int number;
    private int orderId;
    private Date orderDate;
    private double price;

    public Check(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getDate();
        this.price = order.getPrice();
    }

    public void printCheck() {
    }

}
