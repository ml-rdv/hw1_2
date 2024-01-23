package PracticeClassesInterfacesPackagesModules.DeepCopyWithCloneable;

// Класс упрощён
public class Order implements Cloneable {

    protected OrderStatus status;
    protected double price;
    protected Client client;


    public Order(Client client, OrderStatus status, double price) {
        this.client = client;
        this.status = status;
        this.price = price;
    }

    @Override
    public Order clone() {
        try {
            Order clone = (Order) super.clone();
            if (this.client != null) {
                clone.client = this.client.clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
