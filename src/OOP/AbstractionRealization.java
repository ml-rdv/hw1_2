package OOP;

class AbstractionRealization extends Abstraction {

    // переменная i не наследуется из абстр. класса
    public int i;

    // можно расширять доступность, но не в обратную сторону
    @Override
    public void method1() {
        System.out.println(i);
    }

    @Override
    public void method2() {
        super.method2();
    }

    @Override
    public void method3() {
        System.out.println("Overriding of public abstract method");
    }

    public static void main(String[] args) {
        AbstractionRealization ar = new AbstractionRealization();
        ar.method1();
    }
}
