package FunctionalProgramming.Optional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CustomerOptionalService {
    /**
     * Метод должен вернуть адрес в формате страна_город, если покупателю больше 18 лет и у него есть хотя бы 1 заказ.
     * Если покупателю меньше 18 или нет заказов, или нет адреса (отсутствие страны или города, считаем за невалидный кейс), то
     * метод должен вернуть "unknown"
     * <p>
     * Метод может принимать и корректно обрабатывать null
     */
    public String getAddressForView(Customer customer) {
        Optional<Customer> customerOptional = Optional.ofNullable(customer)
                .filter(customer1 -> customer1.age > 18);
        if (customerOptional.isPresent()) {
            Optional<Address> addressOptional = Optional.ofNullable(customerOptional.get().address());
            if (addressOptional.isPresent()) {
                Optional<String> countryOptional = Optional.ofNullable(addressOptional.get().country);
                Optional<String> cituOptional = Optional.ofNullable(addressOptional.get().city);
                if (countryOptional.isPresent()
                        && cituOptional.isPresent()
                        && !customerOptional.get().orders.isEmpty()) {
                    Optional<String> resultOptional = addressOptional
                            .map(address -> address.country() + "_" + address.city());
                    return resultOptional.get();
                }
            }
        }
        return "unknown";
    }

    /**
     * Метод принимает покупателя и заказ
     * Если покупатель == null или заказ == null или заказы покупателя == null, то возвращается Optional.empty(),
     * иначе заказ добавляется к покупателю и метод возвращает покупателя в Optional
     */
    public Optional<Customer> addOrderToCustomer(Customer customer, Order order) {
        Optional<Customer> customerOptional = Optional.ofNullable(customer);
        Optional<Order> orderOptional = Optional.ofNullable(order);
        if (customerOptional.isPresent()) {
            Optional<List<Order>> ordersOptional = Optional.ofNullable(customer.orders);
            if (ordersOptional.isPresent() && orderOptional.isPresent()) {
                customer.orders.add(order);
                return customerOptional;
            }
        }
        return Optional.empty();
    }


    /**
     * Метод вызывает метод addOrderToCustomer()
     * Если addOrderToCustomer возвращает Optional, который isPresent, тогда в консоль выводиться сообщение
     * "заказ успешно добавлен для пользователя {}, кол-во заказов = {}",
     * иначе должно выбрасываться исключение
     */
    public void handleOrder(Customer customer, Order order) {
        Optional<Customer> customerOptional = addOrderToCustomer(customer, order);
        if (customerOptional.isPresent()) {
            System.out.printf("Заказ успешно добавлен для пользователя %s, " +
                    "кол-во заказов = %d", customer.name, customer.orders.size());
        } else {
            throw new NullPointerException("Пользователь не найден");
        }
//        customerOptional.ifPresentOrElse(customer1
//                        -> System.out.printf("Заказ успешно добавлен для пользователя %s, " +
//                        "кол-во заказов = %d", customer1.name, customer1.orders.size()),
//                () -> throw new NullPointerException("Пользователь не найден"));
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