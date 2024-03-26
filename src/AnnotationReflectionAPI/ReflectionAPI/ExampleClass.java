package AnnotationReflectionAPI.ReflectionAPI;

public class ExampleClass implements ExampleInterface {
    private int number;
    private final String name;

    public ExampleClass(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public void printData() {
        System.out.println(number + name);
    }

    private void printString() {
        System.out.println("This is private method");
    }

    @Override
    public String toString() {
        return "ExampleClass{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
