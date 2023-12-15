package PracticeClassesInterfacesPackagesModules.DeepCopyWithCloneable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Выбрать класс из задачи по ООП и реализовать в нём глубокое копирование.
 */

public class DeepCopyWithCloneableTest {
    @Test
    void cloned_order_should_not_be_equals() {
        String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
        LocalDateTime birthday = LocalDateTime.parse("22-1-1994 10:15:55 AM", DateTimeFormatter.ofPattern(DATE_FORMAT));

        Client client = new Client("Mike", 4444444, "mike@gmail.com", birthday);
        Order order = new Order(client, OrderStatus.NEW, 54.8);

        Order orderClone = order.clone();
        orderClone.price = 47.9;
        orderClone.client.phoneNumber = 8888888;

        Assertions.assertNotEquals(order, orderClone);
        Assertions.assertNotEquals(order.price, orderClone.price);
        Assertions.assertNotEquals(order.client.phoneNumber, orderClone.client.phoneNumber);
    }

    @Test
    void orders_should_be_equals() {
        String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
        LocalDateTime birthday = LocalDateTime.parse("22-1-1994 10:15:55 AM", DateTimeFormatter.ofPattern(DATE_FORMAT));

        Client client = new Client("Mike", 4444444, "mike@gmail.com", birthday);
        Order order = new Order(client, OrderStatus.NEW, 54.8);

        Order secondOrder = order;
        secondOrder.price = 47.9;
        secondOrder.client.phoneNumber = 8888888;

        Assertions.assertEquals(order, secondOrder);
        Assertions.assertEquals(order.price, secondOrder.price);
        Assertions.assertEquals(order.client.phoneNumber, secondOrder.client.phoneNumber);
    }
}
