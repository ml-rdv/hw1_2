package Collections;

import InternetCafe.Client;

import java.util.Comparator;

/**
 * Создать свой класс Comparator для класса из задачи по ООП и также написать тест на сортировку
 * В список нужно добавить null’ы и сделать так, чтобы сортировка могла их  обработать
 */

public class ComparatorRealization implements Comparator<Client> {

    public int compare(Client a, Client b) {

        // Вариант 1

//        String fullName1 = a == null ? "" : a.getFullName();
//        String fullName2 = b == null ? "" : b.getFullName();
//
//        // (НЕОЧЕВИДНО С String.valueOf(0), поэтому решение не подходящее)
//        // Для обработки случаев, когда имя = null
//        // (заменяю имя на символ с минимальным аски кодом)
//        fullName1 = fullName1 == null ? String.valueOf(0) : fullName1;
//        fullName2 = fullName2 == null ? String.valueOf(0) : fullName2;

        // Вариант 2

        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }

        String fullName1 = a.getFullName();
        String fullName2 = b.getFullName();

        if (fullName1 == null) {
            return -1;
        }
        if (fullName2 == null) {
            return 1;
        }

        return a.getFullName().compareTo(b.getFullName());
    }
}
