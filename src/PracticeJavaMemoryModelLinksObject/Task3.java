/*
Ссылки на объекты Test копируются и передаются в метод новые ссылки
1)	Новая ссылка на объект test обнуляется, из-за чего в методе null, а в main testField='test'
2)	Новая ссылка на объект test2 изменяет содержимое объекта,
    из-за чего в методе testField='newTest2' и в main testField='newTest2'
3)	Новая ссылка на объект test3 указывает на новый объект
    new Test("newRefTest");
    из-за чего в методе testField='newRefTest', а в main ссылка указывает на старый объект,
    следовательно testField='test3'
*/
package PracticeJavaMemoryModelLinksObject;

public class Task3 {
    public static void main(String[] args) {
        var test = new Test("test");
        var test2 = new Test("test2");
        var test3 = new Test("test3");

        method(test, test2, test3);

        System.out.println("--------------------");

        System.out.println("Вывод в Main: ");
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test3);
    }

    private static void method(Test test, Test test2, Test test3) {
        test = null;
        test2.testField = "newTest2";
        test3 = new Test("newRefTest");

        System.out.println("Вывод в методе: ");
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test3);
    }

    private static class Test {
        private String testField;

        public Test(String testField) {
            this.testField = testField;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "testField='" + testField + '\'' +
                    '}';
        }
    }
}
