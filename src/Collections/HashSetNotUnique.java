package Collections;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Как в данном примере сделать так, что Person в hashSet стал уникальным?
 *
 * Ответ: HashSet может хранить только уникальные значения.
 * Каждый элемент HashSet имеет свой хэш-код, который вычисляется
 * на основе его содержимого. Хэш-код используется для определения индекса элемента.
 *
 * Следовательно, для того, чтобы Person в hashSet стал уникальным, нужно реализовать
 * методы hashCode() и  equals()
 */
class HashSetNotUnique {
    static class Person {
        String name;

        // Метод добавлен
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        // Метод добавлен
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        Person(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public static void main(String args[]) {
        Set<Person> set = new HashSet<>();
        Person ivan = new Person("Иван");
        Person maria = new Person("Мария");
        Person peter = new Person("Пётр");
        Person maria1 = new Person("Мария");

        set.add(ivan);
        set.add(maria);
        set.add(peter);
        set.add(maria1);

        System.out.println(set);
        System.out.println(set.size());
    }
}