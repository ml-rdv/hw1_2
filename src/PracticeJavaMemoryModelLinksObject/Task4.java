package PracticeJavaMemoryModelLinksObject;

import java.util.Date;
import java.util.Objects;
// Check

public class Task4 {
    private int number;
    private int orderId;
    private Date orderDate;
    private double price;

    public Task4(int orderId, Date orderDate, double price) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.price = price;
    }

    public void printCheck() {
    }

    @Override
    public boolean equals(Object o) {
        if(this  == o){
            return true;
        }
        if(o == null || o.getClass() != getClass()) {
            return false;
        }
        return (number == ((Task4) o).number
                && orderDate.equals(((Task4) o).orderDate)
                && (orderId == ((Task4) o).orderId)
                && price == ((Task4) o).price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, orderId, orderDate, price);
    }

    @Override
    public String toString() {
        return "Number = " + number +
                "\nId = " + orderId +
                "\nDate = " + orderDate +
                "\nPrice = " + price;
    }
}