package Collections;

import InternetCafe.Client;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorRealizationTest {

    // Сортировка по fullName
    // Доработать
    // Если имя = null
    @Test
    void should_sort_using_Comparator() {
        final ComparatorRealization comparator = new ComparatorRealization();
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ivan", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(null);
        clients.add(new Client("Maria", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(null);
        clients.add(new Client("Egor", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(null);
        clients.add(new Client("Anastasia", 1, "email", Date.valueOf("1997-03-10")));
//        clients.sort(comparator);
        clients.sort(Comparator.nullsFirst(Comparator.comparing(Client::getFullName)));
        // Если имя = null !!!
        System.out.println(clients);
    }
}
