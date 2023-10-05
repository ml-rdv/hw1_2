package OOP;

public class PolymorphismRealization extends AbstractClass implements PolymorphismInterface1, PolymorphismInterface2{

    @Override
    public void method1() {
        System.out.println(PolymorphismInterface1.i);
        // вызов метода из абстрактого класса
        PolymorphismInterface1.method3();
        method3();
    }

    // когда 2 идентичных дефолтных метода в разных интерфейсах, нужна реализация
    // или вызов метода из конкретного интерфейса
    @Override
    public void method2() {
        PolymorphismInterface1.super.method2();
    }

    // ДОЛЖЕН БЫТЬ РЕАЛИЗОВАТЬ method3() ИЗ АБСТ. КЛАССА, хоть и не показывает ошибку, но выкидывает при компиляции
    // Скорее всего, из-за того, что есть реализация метода с такой сигнатурой в интерфейсе
    @Override
    public void method3(){
        System.out.println("I'm in method3");
    }

    public void abs(){
    }

    public void abs(int i){
    }

    protected void abs(int i, boolean b){
    }

    public static void main(String[] args) {
        PolymorphismRealization pr = new PolymorphismRealization();
        pr.method1();
    }
}
