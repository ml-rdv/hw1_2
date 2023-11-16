package InternetCafe;

import java.util.Date;
import java.util.Objects;

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

    public Check(int orderId, Date orderDate, double price) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.price = price;
    }

    public void printCheck() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        return number == check.number && orderId == check.orderId && Double.compare(price, check.price) == 0 && Objects.equals(orderDate, check.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, orderId, orderDate, price);
    }

    @Override
    public String toString() {
        return "Check{" +
                "number=" + number +
                ", orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", price=" + price +
                '}';
    }
}
