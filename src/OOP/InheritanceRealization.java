package OOP;

public class InheritanceRealization extends Inheritance {

    // можно расширять доступность, но не в обратную сторону
    @Override
    public void method1() {
        // i from parent class
        System.out.println(i);
    }

    @Override
    public void method2() {
        super.method2();
    }

}
