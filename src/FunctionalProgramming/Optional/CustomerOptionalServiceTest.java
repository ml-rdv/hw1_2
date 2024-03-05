package FunctionalProgramming.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import FunctionalProgramming.Optional.CustomerOptionalService.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CustomerOptionalServiceTest {
    @Test
    public void mustReturnAddress() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer = new Customer("Mike", 19, address, List.of(order1, order2));
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        String addressString = customerOptionalService.getAddressForView(customer);
        Assertions.assertEquals("Paris_France", addressString);
    }

    @Test
    public void mustNotReturnAddressBecauseOfAge() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer = new Customer("Mike", 15, address, List.of(order1, order2));
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        String addressString = customerOptionalService.getAddressForView(customer);
        Assertions.assertEquals("unknown", addressString);
    }

    @Test
    public void mustNotReturnAddressBecauseOfCityAbsence() {
        Address address = new Address("Paris", null);
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer = new Customer("Mike", 19, address, List.of(order1, order2));
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        String addressString = customerOptionalService.getAddressForView(customer);
        Assertions.assertEquals("unknown", addressString);
    }

    @Test
    public void mustNotReturnAddressBecauseOfOrdersAbsence() {
        Address address = new Address("Paris", null);
        Customer customer = new Customer("Mike", 19, address, null);
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        String addressString = customerOptionalService.getAddressForView(customer);
        Assertions.assertEquals("unknown", addressString);
    }

    @Test
    public void mustAddOrderToCustomer() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        Customer customer = new Customer("Mike", 19, address, orders);
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        Optional<Customer> customerOptional = customerOptionalService.addOrderToCustomer(customer, order2);
        Assertions.assertTrue(customerOptional.isPresent());
        Assertions.assertEquals(customer.orders().size(), 2);
    }

    @Test
    public void mustNotAddOrderToCustomerBecauseOfOrdersAbsence() {
        Address address = new Address("Paris", "France");
        Product product = new Product("Product", 465.75);
        Order order = new Order("1", List.of(product), java.math.BigDecimal.valueOf(132));
        Customer customer = new Customer("Mike", 19, address, null);
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        Optional<Customer> customerOptional = customerOptionalService.addOrderToCustomer(customer, order);
        Assertions.assertTrue(customerOptional.isEmpty());
    }

    @Test
    public void mustNotAddOrderToCustomerBecauseOfOrderAbsence() {
        Address address = new Address("Paris", "France");
        Product product = new Product("Product", 1334.35);
        Order order = new Order("1", List.of(product), java.math.BigDecimal.valueOf(486));
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        Customer customer = new Customer("Mike", 19, address, orders);
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        Optional<Customer> customerOptional = customerOptionalService.addOrderToCustomer(customer, null);
        Assertions.assertTrue(customerOptional.isEmpty());
    }

    @Test
    public void mustNotAddOrderToCustomerBecauseOfCustomerAbsence() {
        Product product = new Product("Product", 465.75);
        Order order = new Order("1", List.of(product), java.math.BigDecimal.valueOf(132));
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        Optional<Customer> customerOptional = customerOptionalService.addOrderToCustomer(null, order);
        Assertions.assertTrue(customerOptional.isEmpty());
    }

    @Test
    public void mustPrintInfoAboutOrder() {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        Customer customer = new Customer("Mike", 19, address, orders);
        CustomerOptionalService customerOptionalService = new CustomerOptionalService();
        customerOptionalService.handleOrder(customer, order2);

        var consoleOutput = byteArrayOutputStream.toString();
        String result = "Заказ успешно добавлен для пользователя Mike, кол-во заказов = 2";

        Assertions.assertEquals(result, consoleOutput);
    }

    @Test
    public void mustThrowNullPointerExceptionBecauseOfOrderAbsence() {
        Exception exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            Address address = new Address("Paris", "France");
            Product product = new Product("Product", 1334.35);
            Order order = new Order("1", List.of(product), java.math.BigDecimal.valueOf(486));
            List<Order> orders = new ArrayList<>();
            orders.add(order);
            Customer customer = new Customer("Mike", 19, address, orders);
            CustomerOptionalService customerOptionalService = new CustomerOptionalService();
            customerOptionalService.handleOrder(customer, null);
        });

        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}