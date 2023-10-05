package OOP;

public class Inheritance {

    protected int i;

    protected void method1(){
        System.out.println("1");
    }

    public void method2(){
        System.out.println("2");
    }

    public void method3(){
        System.out.println("3");
    }

//    Статические методы не переопределяются, но их можно перекрыть.
//    В данном случае скорей правильнее будет сказать,
//    что у нас есть к доступ к родительскому статическому методу через дочерний класс
    public static void method4(){
        System.out.println("4");
    }
}
