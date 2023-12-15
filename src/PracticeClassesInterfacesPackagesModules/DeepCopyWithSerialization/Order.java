package PracticeClassesInterfacesPackagesModules.DeepCopyWithSerialization;

import java.io.Serializable;
import java.util.Date;

// Класс упрощён
public class Order implements Serializable {

    protected OrderStatus status;
    protected double price;
    protected Client client;

    public Order(Client client, OrderStatus status, double price) {
        this.client = client;
        this.status = status;
        this.price = price;
    }
}
