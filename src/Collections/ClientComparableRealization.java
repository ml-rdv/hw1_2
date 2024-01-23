package Collections;

import java.util.Objects;

/**
 * Выбрать класс из задачи по ООП и реализовать в нём интерфейс Comparable.
 * Затем написать тест в котором сортируется список с объектами класса из задачи по  ООП
 */
public class ClientComparableRealization implements Comparable<ClientComparableRealization> {
    private final int id;
    private final String fullName;

    public ClientComparableRealization(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientComparableRealization that = (ClientComparableRealization) o;
        return id == that.id && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(ClientComparableRealization o) {
        if (this.id > o.getId()) {
            return 1;
        } else if (o.getId() > this.id) {
            return -1;
        } else {
            return 0;
        }
        // Вариант 2
//        return this.id - o.getId();

        // Вариант 3
//        return Integer.compare(this.id, o.getId());
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }

}
