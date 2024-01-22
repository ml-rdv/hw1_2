package Collections;

import InternetCafe.Client;

import java.util.Comparator;

/**
 * Создать свой класс Comparator для класса из задачи по ООП и также написать тест на сортировку
 * В список нужно добавить null’ы и сделать так, чтобы сортировка могла их  обработать
 */

// ДОРАБОТАТЬ
// ПРОБЛЕМА С NULL

public class ComparatorRealization implements Comparator<Client> {

    public int compare(Client a, Client b){

        String fullName1 = a == null ? null : a.getFullName();
        String fullName2 = b == null ? null : b.getFullName();

        return fullName1.compareTo(fullName2);
    }
}
