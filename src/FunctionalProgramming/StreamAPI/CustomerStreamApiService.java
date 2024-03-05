package FunctionalProgramming.StreamAPI;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static java.util.function.Predicate.not;

public class CustomerStreamApiService {
    /**
     * Метод должен вернуть список адресов покупателей, которым больше 18 лет
     */
    public List<Address> getAdultAddresses(List<Customer> customers) {
        return customers.stream()
                .filter(customer -> customer.age > 18)
                .map(customer -> customer.address)
                .toList();
    }

    /**
     * Метод должен вывести в консоль покупателей по очереди в формате "имя, возраст, кол-во заказов".
     * При этом покупатели должны быть отсортированы по возрасту
     */
    public void printCustomers(List<Customer> customers) {
        customers.stream()
                .sorted(Comparator.comparing(customer -> customer.age))
                .forEach(customer -> System.out.printf("%s, %d, %d\n",
                        customer.name,
                        customer.age,
                        customer.orders.size()));
    }

    /**
     * Метод должен вернуть строку состоящую из имён покупателей, которые разделены строкой "|"
     */
    public String getAllNameCustomers(List<Customer> customers) {
        return customers.stream()
                .map(customer -> customer.name)
                .collect(Collectors.joining(" / "));
    }

    /**
     * Метод должен вернуть самого старшего покупателя,
     * если покупателй нет, то выбросить исключение
     */
    public Customer getOldestCustomer(List<Customer> customers) {
        return customers.stream()
                .max(Comparator.comparing(customer -> customer.age))
                .orElseThrow();
    }

    /**
     * Метод должен вернуть список уникальных имён покупателей (можно реализовать двумя способами)
     */
    public Collection<String> getUniqueNames(List<Customer> customers) {
        return customers.stream()
                .map(customer -> customer.name)
                .distinct()
                .toList();
    }

    /**
     * Метод должен вернуть кол-во покупателей, у которых кол-во заказав равно ordersNum
     */
    public long countCustomersByOrdersNum(List<Customer> customers, int ordersNum) {
        return customers.stream()
                .filter(customer -> customer.orders.size() == ordersNum)
                .count();
    }

    /**
     * Метод должен вернуть сумму кол-ва всех заказов всех покупателей,
     * если заказы == null, то таких покупателей нужно исключить
     */
    public int sumAllOrderSize(List<Customer> customers) {
        return customers.stream()
                .filter(not(customer -> customer.orders == null))
                .mapToInt(customer -> customer.orders.size())
                .sum();
    }


    /**
     * Метод должен вернуть общую стоимость всех заказов у покупателя.
     * Избавляться от BigDecimal в пользу double или float нельзя.
     */
    public BigDecimal countTotalPriceCustomer(Customer customer) {
        List<Order> orders = customer.orders;
        return orders.stream()
                .map(order -> order.totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Метод должен вернуть map, где ключ будет id заказа, а значение общая стоимость.
     * Также заказы должны быть получены с учётом аргументов limit и skip
     */
    public Map<String, BigDecimal> getOrderIdToTotalPrice(List<Customer> customers, int limit, int skip) {
        Map<String, BigDecimal> prices = new HashMap<>();
        customers.stream()
                .flatMap(customer -> customer.orders().stream())
                .limit(limit)
                .skip(skip)
                .forEach(order -> prices.put(order.orderId, order.totalPrice));
        return prices;
    }

    /**
     * Метод должен сгруппировать покупателей по странам и вернуть map,
     * где ключ это страна, а значение список покупателей
     */
    public Map<String, List<Customer>> groupCustomersByCountry(List<Customer> customers) {
        return customers.stream()
                .collect(Collectors.groupingBy(
                        customer -> customer.address().country()));
    }

    /**
     * Метод должен вернуть любой продукт из заказов покупателей
     */
    public Optional<Product> findProductOpt(List<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders.stream())
                .flatMap(order -> order.products.stream())
                .findAny();
    }

    /**
     * Метод должен вернуть первую найденную стоимость продукта из заказов покупателей,
     * если стоимость не найдена, то вернуть 0
     */
    public double findProductPrice(List<Customer> customers) {
        return customers.stream()
                .flatMap(customer -> customer.orders.stream())
                .flatMap(order -> order.products.stream())
                .flatMapToDouble(product -> DoubleStream.of(product.productPrice))
                .findAny()
                .orElse(0.0);
    }

    /**
     * Метод должен вернуть true, если все покупатели совершеннолетние
     */
    public boolean hasOnlyAdultCustomers(List<Customer> customers) {
        return customers.stream().allMatch(customer -> customer.age >= 18);
    }

    /**
     * Метод должен вернуть true, если хотя бы 1 покупатель имеет заказ
     */
    public boolean hasCustomerWithOrder(List<Customer> customers) {
        return customers.stream().anyMatch(customer -> !customer.orders.isEmpty());
    }

    public record Customer(
            String name,
            int age,
            Address address,
            List<Order> orders
    ) {
    }

    public record Address(String country, String city) {
    }

    public record Order(String orderId, List<Product> products, BigDecimal totalPrice) {
    }

    public record Product(String productName, double productPrice) {
    }
}