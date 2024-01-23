package Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClientComparableRealizationTest {

    // Сортировка по  id
    @Test
    void should_sort_using_Comparable() {
        List<ClientComparableRealization> clients = new ArrayList<>();
        clients.add(new ClientComparableRealization(4, "Ivan"));
        clients.add(new ClientComparableRealization(2, "Maria"));
        clients.add(new ClientComparableRealization(12, "Egor"));
        clients.add(new ClientComparableRealization(1, "Anastasia"));
        Collections.sort(clients);

        // Вариант 2
        // null`ы вначале, сортировка по id, если id равны, то сортировка по fullName
        clients.sort(Comparator.nullsFirst
                (Comparator.comparing(
                        (ClientComparableRealization client) -> client.getId()).
                thenComparing(
                        (ClientComparableRealization client) -> client.getFullName())));

        System.out.println(clients);

        List<ClientComparableRealization> clients2 = new ArrayList<>();
        clients2.add(new ClientComparableRealization(1, "Anastasia"));
        clients2.add(new ClientComparableRealization(2, "Maria"));
        clients2.add(new ClientComparableRealization(4, "Ivan"));
        clients2.add(new ClientComparableRealization(12, "Egor"));
        Assertions.assertEquals(clients2, clients);
    }
}
