package AnnotationReflectionAPI.ReflectionAPI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionClass {
    public static void main(String[] args) {

        ExampleClass ec = new ExampleClass(1, "Ivan");
        printInfo(ec.getClass());

        createObjectAndChangePrivateValue();
    }

    /**
     * 1. Реализовать метод, который принимает класс и выводит в консоль:
     * название класса
     * поля класса вместе с типом и модификатором доступа
     * названия всех методов
     * кол-во интерфейсов, который класс реализует
     */
    private static <T> void printInfo(Class<T> cl) {
        System.out.println("Class name:");
        String className = cl.getName();
        System.out.println(className);

        System.out.println("\nFields:");
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Name: " + field.getName() + "\n"
                    + "\tModifier: " + getModifier(field) + "\n"
                    + "\tType: " + field.getType());
        }

        System.out.println("\nMethods:");
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.printf("\nNumber of implemented interfaces: %d\n\n\n", cl.getInterfaces().length);
    }

    private static String getModifier(Field field) {
        if (Modifier.isPublic(field.getModifiers())) {
            return "public";
        } else if (Modifier.isProtected(field.getModifiers())) {
            return "protected";
        } else if (Modifier.isPrivate(field.getModifiers())) {
            return "private";
        } else {
            return "default";
        }
    }

    /**
     * 2. С помощью рефлексии нужно изменить значение приватного поля.
     * 3. Создать экземпляр класса с помощью рефлексии.
     */
    private static void createObjectAndChangePrivateValue() {
        // Создание экземпляра класса с помощью рефлексии
        Class<?> clazz;
        ExampleClass myClass;
        try {
            clazz = Class.forName(ExampleClass.class.getName());
            Class<?>[] params = {int.class, String.class};
            myClass = (ExampleClass) clazz.getConstructor(params).newInstance(1, "John");

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        System.out.println(myClass);

        Field[] fields = ExampleClass.class.getDeclaredFields();
        for (Field field : fields) {
            // Изменение значение приватного поля
            if (field.getName().equals("name")) {
                field.setAccessible(true); // Даю доступ для работы с приватным полем
                try {
                    field.set(myClass, "Mark"); // Меняю значение
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(myClass);
    }
}
