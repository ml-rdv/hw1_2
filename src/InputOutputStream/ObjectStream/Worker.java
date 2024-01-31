package InputOutputStream.ObjectStream;

import java.io.Serializable;

public class Worker implements Serializable {
    String name;
    int age;

    public Worker(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
