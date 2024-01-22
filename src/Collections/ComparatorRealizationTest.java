package Collections;

import InternetCafe.Client;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// ДОРАБОТАТЬ
// ПРОБЛЕМА С NULL

public class ComparatorRealizationTest {

    // ДОРАБОТАТЬ
    // Сортировка по fullName
    @Test
    void should_sort_using_Comparator() {
        final ComparatorRealization comparator = new ComparatorRealization();
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ivan", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(new Client("Maria", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(new Client("Egor", 1, "email", Date.valueOf("1997-03-10")));
        clients.add(new Client("Anastasia", 1, "email", Date.valueOf("1997-03-10")));
        // ДОРАБОТАТЬ
        clients.add(null);
        clients.sort(comparator);
        System.out.println(clients);
    }
}
