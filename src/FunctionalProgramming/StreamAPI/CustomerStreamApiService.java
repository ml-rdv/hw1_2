package FunctionalProgramming.StreamAPI;

import java.math.BigDecimal;
import java.util.*;

public class CustomerStreamApiService {
    /**
     * Метод должен вернуть список адресов покупателей, которым больше 18 лет
     */
//    public List<Address> getAdultAddresses(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вывести в консоль покупателей по очереди в формате "имя, возраст, кол-во заказов".
//     * При этом покупатели должны быть отсортированы по возрасту
//     */
//    public void printCustomers(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть строку состоящую из имён покупателей, которые разделены строкой " | "
//     */
//    public String getAllNameCustomers(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть самого старшего покупателя,
//     * если покупателй нет, то выбросить исключение
//     */
//    public Customer getOldestCustomer(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть список уникальных имён покупателей (можно реализовать двумя способами)
//     */
//    public Collection<String> getUniqueNames(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть кол-во покупателей, у которых кол-во заказав равно ordersNum
//     */
//    public long countCustomersByOrdersNum(List<Customer> customers, int ordersNum) {
//
//    }
//
//    /**
//     * Метод должен вернуть сумму кол-ва всех заказов всех покупателей,
//     * если заказы == null, то таких покупателей нужно исключить
//     */
//    public int sumAllOrderSize(List<Customer> customers) {
//
//    }
//
//
//    /**
//     * Метод должен вернуть общую стоимость всех заказов у покупателя.
//     * Избавляться от BigDecimal в пользу double или float нельзя.
//     */
//    public BigDecimal countTotalPriceCustomer(Customer customer) {
//
//    }
//
//    /**
//     * Метод должен вернуть map, где ключ будет id заказа, а значение общая стоимость.
//     * Также заказы должны быть получены с учётом аргументов limit и skip
//     */
//    public Map<String, BigDecimal> getOrderIdToTotalPrice(List<Customer> customers, int limit, int skip) {
//
//    }
//
//    /**
//     * Метод должен сгрупировать покупателей по странам и вернуть map,
//     * где ключ это страна, а значение список покупателей
//     */
//    public Map<String, List<Customer>> groupCustomersByCountry(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть любой продукт из заказов покупателей
//     */
//    public Optional<Product> findProductOpt(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть первую найденную стоимость продукта из заказов покупателей,
//     * если стоимость не найдена, то вернуть 0
//     */
//    public double findProductPrice(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть true, если все покупатели совершеннолетние
//     */
//    public boolean hasOnlyAdultCustomers(List<Customer> customers) {
//
//    }
//
//    /**
//     * Метод должен вернуть true, если хотя бы 1 покупатель имеет заказ
//     */
//    public boolean hasCustomerWithOrder(List<Customer> customers) {
//
//    }

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