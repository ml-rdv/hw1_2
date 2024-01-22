package Collections;

import java.util.HashMap;
import java.util.Objects;

/**
 * Запустить код и объяснить почему hashmap не отдаёт значение при втором вызове метода get()
 * <p>
 * Переменная num влияет на формирование хэшкода.
 * Значение переменной num изменяется, в следствии чего hashCode() переменной key
 * отличается от хэшкода переменной, которая изначально помещалась в hashMap.
 * <p>
 * Соответсвенно, когда мы хотим вывести hashMap.get(key) по измененному ключу, возвращается
 * значение null, т.к. по данному ключу (который вычисляется по hashCode()) отсутсвует value.
 */
public class MutableKeyInHashMap {
    static class MapTestObject {
        String str = "key";
        int num = 0;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MapTestObject that)) return false;

            if (num != that.num) return false;
            return Objects.equals(str, that.str);
        }

        @Override
        public int hashCode() {
            int result = str != null ? str.hashCode() : 0;
            result = 31 * result + num;
            return result;
        }

        @Override
        public String toString() {
            return "MapTestObject{" +
                    "str='" + str + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    public static void main(String[] args) {
        var hashMap = new HashMap<MapTestObject, MapTestObject>();
        var key = new MapTestObject();
        var value = new MapTestObject();

        hashMap.put(key, value);
        System.out.println(hashMap.get(key));

        key.num = 4;
        System.out.println(hashMap.get(key));
    }
}