package FunctionalProgramming.StreamAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import FunctionalProgramming.StreamAPI.CustomerStreamApiService.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.*;


public class CustomerStreamApiServiceTest {
    @Test
    public void mustGetAdultAddresses() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Customer customer1 = new Customer("Mike", 19, address1, List.of());
        Customer customer2 = new Customer("Mike", 27, address1, List.of());
        Customer customer3 = new Customer("John", 15, address2, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        List<Address> adultAddresses = streamApiService
                .getAdultAddresses(List.of(customer1, customer2, customer3));

        Assertions.assertTrue(adultAddresses.contains(address1));
        Assertions.assertFalse(adultAddresses.contains(address2));
    }

    @Test
    public void mustPrintCustomers() {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Order order3 = new Order("3", List.of(product2), java.math.BigDecimal.valueOf(825));
        Order order4 = new Order("4", List.of(product2), java.math.BigDecimal.valueOf(7251));
        Order order5 = new Order("5", List.of(product2), java.math.BigDecimal.valueOf(545));
        Customer customer1 = new Customer("Mike", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike", 27, address1, List.of(order4, order5));
        Customer customer3 = new Customer("John", 15, address2, List.of(order3));

        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        streamApiService
                .printCustomers(List.of(customer1, customer2, customer3));

        var consoleOutput = byteArrayOutputStream.toString();
        Assertions.assertEquals(
                """
                        John, 15, 1
                        Mike, 19, 2
                        Mike, 27, 2
                        """,
                consoleOutput
        );
    }

    @Test
    public void mustGetAllNameCustomers() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Customer customer1 = new Customer("Mike", 19, address1, List.of());
        Customer customer2 = new Customer("Mike", 27, address1, List.of());
        Customer customer3 = new Customer("John", 15, address2, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        String allNameCustomers = streamApiService
                .getAllNameCustomers(List.of(customer1, customer2, customer3));

        System.out.println(allNameCustomers);
        Assertions.assertEquals("Mike / Mike / John", allNameCustomers);
    }

    @Test
    public void mustThrowExceptionBecauseOfCustomerAbsence() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            CustomerStreamApiService streamApiService = new CustomerStreamApiService();
            streamApiService.getOldestCustomer(List.of());
        });
    }

    @Test
    public void mustGetOldestCustomer() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Customer customer1 = new Customer("Mike", 19, address1, List.of());
        Customer customer2 = new Customer("Mike", 27, address1, List.of());
        Customer customer3 = new Customer("John", 15, address2, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        Customer oldestCustomer = streamApiService
                .getOldestCustomer(List.of(customer1, customer2, customer3));

        Assertions.assertEquals(customer2, oldestCustomer);
    }

    @Test
    public void mustGetUniqueNames() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Order order3 = new Order("3", List.of(product2), java.math.BigDecimal.valueOf(825));
        Order order4 = new Order("4", List.of(product2), java.math.BigDecimal.valueOf(7251));
        Order order5 = new Order("5", List.of(product2), java.math.BigDecimal.valueOf(545));
        Customer customer1 = new Customer("Mike", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike", 19, address1, List.of(order4, order5));
        Customer customer3 = new Customer("John", 19, address2, List.of(order3));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        Collection<String> uniqueNames = streamApiService
                .getUniqueNames(List.of(customer1, customer2, customer3));

        Assertions.assertTrue(uniqueNames.contains("Mike"));
        Assertions.assertTrue(uniqueNames.contains("John"));
    }

    @Test
    public void mustCountCustomersByOrdersNum() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Order order3 = new Order("3", List.of(product2), java.math.BigDecimal.valueOf(825));
        Order order4 = new Order("4", List.of(product2), java.math.BigDecimal.valueOf(7251));
        Order order5 = new Order("5", List.of(product2), java.math.BigDecimal.valueOf(545));
        Customer customer1 = new Customer("Mike1", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike2", 19, address1, List.of(order4, order5));
        Customer customer3 = new Customer("Mike3", 19, address2, List.of(order3));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        long countCustomers = streamApiService
                .countCustomersByOrdersNum(List.of(customer1, customer2, customer3), 2);

        Assertions.assertEquals(2, countCustomers);
    }

    @Test
    public void mustSumAllOrderSize() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Order order3 = new Order("3", List.of(product2), java.math.BigDecimal.valueOf(825));
        Customer customer1 = new Customer("Mike1", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike2", 19, address1, null);
        Customer customer3 = new Customer("Mike3", 19, address2, List.of(order3));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        int sumAllOrderSize = streamApiService
                .sumAllOrderSize(List.of(customer1, customer2, customer3));

        Assertions.assertEquals(3, sumAllOrderSize);
    }

    @Test
    public void mustCountTotalPriceCustomer() {
        Address address1 = new Address("France", "Paris");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer1 = new Customer("Mike1", 19, address1, List.of(order1, order2));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        BigDecimal totalPriceCustomer = streamApiService
                .countTotalPriceCustomer(customer1);

        Assertions.assertEquals(BigDecimal.valueOf(618), totalPriceCustomer);
    }

    @Test
    public void mustGetOrderIdToTotalPrice() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Order order3 = new Order("3", List.of(product2), java.math.BigDecimal.valueOf(825));
        Order order4 = new Order("4", List.of(product2), java.math.BigDecimal.valueOf(7251));
        Order order5 = new Order("5", List.of(product2), java.math.BigDecimal.valueOf(545));
        Customer customer1 = new Customer("Mike1", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike2", 19, address1, List.of(order4, order5));
        Customer customer3 = new Customer("Mike3", 19, address2, List.of(order3));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        Map<String, BigDecimal> prices = streamApiService
                .getOrderIdToTotalPrice(List.of(customer1, customer2, customer3), 4, 1);

        Assertions.assertEquals(BigDecimal.valueOf(132), prices.get("2"));
        Assertions.assertEquals(BigDecimal.valueOf(545), prices.get("5"));
        Assertions.assertEquals(BigDecimal.valueOf(7251), prices.get("4"));
        Assertions.assertNull(prices.get("1"));
        Assertions.assertNull(prices.get("3"));
    }

    @Test
    public void mustGroupCustomersByCountry() {
        Address address1 = new Address("France", "Paris");
        Address address2 = new Address("Italy", "Rome");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer1 = new Customer("Mike1", 19, address1, List.of(order1, order2));
        Customer customer2 = new Customer("Mike2", 19, address1, List.of(order1));
        Customer customer3 = new Customer("Mike3", 19, address2, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        Map<String, List<Customer>> countries = streamApiService
                .groupCustomersByCountry(List.of(customer1, customer2, customer3));

        Assertions.assertEquals(List.of(customer1, customer2), countries.get("France"));
        Assertions.assertEquals(List.of(customer3), countries.get("Italy"));
    }

    @Test
    public void mustFindProductOpt() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer1 = new Customer("Mike", 19, address, List.of(order1, order2));
        Customer customer2 = new Customer("Mike", 19, address, List.of(order1));
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        Optional<Product> productOpt = streamApiService
                .findProductOpt(List.of(customer1, customer2));

        //noinspection OptionalGetWithoutIsPresent
        Assertions.assertEquals(product1, productOpt.get());
    }

    @Test
    public void mustThrowExceptionBecauseOfProductAbsence() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            Address address = new Address("Paris", "France");
            Order order1 = new Order("1", List.of(), java.math.BigDecimal.valueOf(486));
            Customer customer1 = new Customer("Mike", 19, address, List.of(order1));
            CustomerStreamApiService streamApiService = new CustomerStreamApiService();

            Optional<Product> productOpt = streamApiService
                    .findProductOpt(List.of(customer1));
            //noinspection OptionalGetWithoutIsPresent
            productOpt.get();
        });
    }

    @Test
    public void mustFindProductPrice() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer1 = new Customer("Mike", 19, address, List.of(order1, order2));
        Customer customer2 = new Customer("Mike", 19, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        double price = streamApiService.findProductPrice(List.of(customer1, customer2));

        Assertions.assertEquals(1334.35, price);
    }

    @Test
    public void mustNotFindProductPrice() {
        Address address = new Address("Paris", "France");
        Order order1 = new Order("1", List.of(), java.math.BigDecimal.valueOf(486));
        Customer customer1 = new Customer("Mike", 19, address, List.of(order1));
        Customer customer2 = new Customer("Mike", 19, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        double price = streamApiService.findProductPrice(List.of(customer1, customer2));

        Assertions.assertEquals(0.0, price);
    }

    @Test
    public void mustReturnTrueBecauseHasCustomerWithOrder() {
        Address address = new Address("Paris", "France");
        Product product1 = new Product("Product1", 1334.35);
        Product product2 = new Product("Product2", 465.75);
        Order order1 = new Order("1", List.of(product1, product2), java.math.BigDecimal.valueOf(486));
        Order order2 = new Order("2", List.of(product2), java.math.BigDecimal.valueOf(132));
        Customer customer1 = new Customer("Mike", 19, address, List.of(order1, order2));
        Customer customer2 = new Customer("Mike", 19, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        boolean hasCustomerWithOrder = streamApiService
                .hasCustomerWithOrder(List.of(customer1, customer2));

        Assertions.assertTrue(hasCustomerWithOrder);
    }

    @Test
    public void mustReturnFalseBecauseHasNotCustomerWithOrder() {
        Address address = new Address("Paris", "France");
        Customer customer1 = new Customer("Mike", 19, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        boolean hasCustomerWithOrder = streamApiService
                .hasCustomerWithOrder(List.of(customer1));

        Assertions.assertFalse(hasCustomerWithOrder);
    }

    @Test
    public void mustReturnTrueBecauseHasOnlyAdultCustomers() {
        Address address = new Address("Paris", "France");
        Customer customer1 = new Customer("Mike", 19, address, List.of());
        Customer customer2 = new Customer("Mike", 27, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        boolean hasOnlyAdultCustomers = streamApiService
                .hasOnlyAdultCustomers(List.of(customer1, customer2));

        Assertions.assertTrue(hasOnlyAdultCustomers);
    }

    @Test
    public void mustReturnFalseBecauseHasNotOnlyAdultCustomers() {
        Address address = new Address("Paris", "France");
        Customer customer1 = new Customer("Mike", 19, address, List.of());
        Customer customer2 = new Customer("Mike", 15, address, List.of());
        CustomerStreamApiService streamApiService = new CustomerStreamApiService();

        boolean hasOnlyAdultCustomers = streamApiService
                .hasOnlyAdultCustomers(List.of(customer1, customer2));

        Assertions.assertFalse(hasOnlyAdultCustomers);
    }
}