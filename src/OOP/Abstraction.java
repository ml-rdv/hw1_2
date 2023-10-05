package OOP;

public abstract class Abstraction {
    public int i = 5;
    protected abstract void method1();

    public void method2(){
    }

    public abstract void method3();

    // Статич. метод не может быть абстр., т.к. он не переопределяется в потомкам,
    // следовательно, требует реализации
    public static void method4(){
    }

}

