package PracticeClassesInterfacesPackagesModules.DeepCopyWithSerialization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Выбрать класс из задачи по ООП и реализовать в нём глубокое копирование.
 */

public class DeepCopyWithSerializationTest {

    @Test
    void cloned_order_should_not_be_equals() {
        String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
        LocalDateTime birthday = LocalDateTime.parse("22-1-1994 10:15:55 AM", DateTimeFormatter.ofPattern(DATE_FORMAT));

        Client client = new Client("Mike", 4444444, "mike@gmail.com", birthday);
        Order order = new Order(client, OrderStatus.NEW, 54.8);

        Order orderClone = (Order) deepCopy(order);
        orderClone.price = 47.9;
        orderClone.client.phoneNumber = 8888888;

        Assertions.assertNotEquals(order, orderClone);
        Assertions.assertNotEquals(order.price, orderClone.price);
        Assertions.assertNotEquals(order.client.phoneNumber, orderClone.client.phoneNumber);
    }

    public static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
