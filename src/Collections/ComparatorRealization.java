package Collections;

import InternetCafe.Client;

import java.util.Comparator;

/**
 * Создать свой класс Comparator для класса из задачи по ООП и также написать тест на сортировку
 * В список нужно добавить null’ы и сделать так, чтобы сортировка могла их  обработать
 */

public class ComparatorRealization implements Comparator<Client> {

    public int compare(Client a, Client b) {

        String fullName1 = a == null ? "" : a.getFullName();
        String fullName2 = b == null ? "" : b.getFullName();

        // Вариант 2
        /*
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }

        String fullName1 = a.getFullName();
        String fullName2 = b.getFullName();
        */

        return fullName1.compareTo(fullName2);
    }
}
