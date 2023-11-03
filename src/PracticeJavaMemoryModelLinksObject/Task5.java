package PracticeJavaMemoryModelLinksObject;

public class Task5 {
    public static void main(String[] args) {
        String str = "test";
        String str2 = "test";

        // сравниваются ссылки, однако ссылки равны, т.к. указывают на один и ту же строку в String Pool
        System.out.println(str == str2);

        // сравнивается содержимое строк, строки равны
        System.out.println(str.equals(str2));

        String str3 = new String("test");

        /* сравниваются ссылки, однако ссылки не равны, т.к. ссылка str3 указывает на новую строку
        new String("test")
        То есть, в String Pool добавилась ещё одна строка с содержимым
        “test”. И ссылка на неё отличается от ссылки str */
        System.out.println(str == str3);

        //сравнивается содержимое строк, строки равны
        System.out.println(str.equals(str3));

        /* Метод intern просто перед созданием объекта String смотрит есть ли этот объект в пуле стрингов
        и возвращает его. Иначе создается новый объект в пуле.
        new String("Hello").intern() == new String("Hello").intern() возвращает true
        Следовательно, сравнение в 13 и 14 строке возвращает true */
        System.out.println(str == str3.intern());
        System.out.println(str.equals(str3.intern()));
    }
}
