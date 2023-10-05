package OOP;

interface PolymorphismInterface1 {
    public int i = 2;

    public void method1();

    public default void method2(){
    }

    // статический метод не переопределяется в потомниках
    public static void method3(){
        method4();
    }

    // приватный метод не виден в классе-потомнике
    private static void method4() {
        System.out.println("I'm in PolymorphismInterface1");
    }
}

interface PolymorphismInterface2 {
    public int i = 2;

    public void method1();

    public default void method2(){
    }

    public static void method3(){
        method4();
    }

    private static void method4() {
        System.out.println("I'm in PolymorphismInterface2");
    }
}

interface PolymorphismInterface3 extends PolymorphismInterface1 {
    public int i = 2;

    public void method1();

    // Метод не переопределяется, т.к. статический. Принадлежит конкретно этому интерфейсу
    public static void method3(){
        PolymorphismInterface1.method3();
    }

    // Метод не переопределяется, т.к. приватный. Принадлежит конкретно этому интерфейсу
    private void method4() {
        PolymorphismInterface1.method3();
    }
}

abstract class AbstractClass {
    public int i = 5;
    protected abstract void method1();

    public void method2(){
    }

    public abstract void method3();

}
