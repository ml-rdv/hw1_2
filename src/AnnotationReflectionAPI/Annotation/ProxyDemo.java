package AnnotationReflectionAPI.Annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * С помощью своей аннотации и класса java.lang.reflect.Proxy реализовать вывод в консоль
 * аргументов методов и возвращаемое значение.
 * Создать аннотацию, которая вешается на класс. В аннотации должно быть свойство enable
 * Для работы с классом Proxy нужно создать интерфейс и реализовать его в классе, на который
 * будет вешаться аннотация
 * В прокси класса реализуется логика по выводу в консоль. Если свойство аннотации enable = false,
 * то вывод в консоль не должен отрабатывать, либо не должен создаваться прокси
 * <p>
 * Теория:
 * <p>
 * Прокси-класс — это некоторая «надстройка» над оригинальным классом,
 * которая позволяет нам при необходимости изменить его поведение.
 * <p>
 * Создание прокси объекта происходит на уровне интерфейсов, а не классов.
 * Прокси создается для интерфейса.
 * Наличие интерфейса — обязательное требование.
 * (только для java.lang.reflect.Proxy, есть другие способы создание прокси
 * и там наличие интерфейсов не обязательны)
 * <p>
 * InvocationHandler — это специальный интерфейс, который позволяет перехватить любые
 * вызовы методов к нашему объекту и добавить нужное нам дополнительное поведение.
 * <p>
 * Метод invoke() перехватывает все вызовы методов к нашему объекту и добавляет необходимое поведение.
 */

@SuppressWarnings("ALL")
public class ProxyDemo {

    interface SomeInterface {
        String originalMethod(int num);
    }

    @MyAnnotation(enable = true)
    static class OriginalEnabled implements SomeInterface {
        @Override
        public String originalMethod(int num) {
            System.out.println("I`m in method");
            return "int = " + num;
        }
    }

    @MyAnnotation
    static class OriginalNotEnabled implements SomeInterface {
        @Override
        public String originalMethod(int num) {
            System.out.println("I`m in method");
            return "int = " + num;
        }
    }

    static class MyHandler implements InvocationHandler {
        private final SomeInterface original;

        public MyHandler(SomeInterface original) {
            this.original = original;
        }

        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
            if (enableIsTrue(original.getClass())) {
                System.out.println("Arguments: " + Arrays.toString(args));
                Object returnValue = method.invoke(original, args);
                System.out.println("Return value: " + returnValue);
                return returnValue;
            } else {
                return method.invoke(original, args);
            }
        }

        private static <T> boolean enableIsTrue(Class<T> cl) {
            MyAnnotation annotation = cl.getAnnotation(MyAnnotation.class);
            return annotation != null && annotation.enable();
        }
    }

    private static SomeInterface createProxy(SomeInterface original) {
        //Создаем перехватчик методов
        MyHandler myHandler = new MyHandler(original);

        //Получаем загрузчик класса у оригинального объекта
        ClassLoader classLoader = SomeInterface.class.getClassLoader();

        //Получаем все интерфейсы, которые реализует оригинальный объект
        // Class[] interfaces = original.getClass().getInterfaces();
        // Заменила на new Class[]{SomeInterface.class},
        // т.к. не для всех интерфейсов может понадобиться прокся, а только для SomeInterface

        //Создаем прокси нашего объекта original
        SomeInterface originalProxy = (SomeInterface) Proxy.newProxyInstance(classLoader,
                new Class[]{SomeInterface.class},
                myHandler);
        return originalProxy;
    }

    public static void main(String[] args) {
        System.out.println("Class with enable = false");
        OriginalNotEnabled original = new OriginalNotEnabled();
        //Создаем прокси нашего объекта original
        SomeInterface originalProxy1 = createProxy(original);
        //Вызываем у прокси объекта один из методов нашего оригинального объекта
        System.out.println(originalProxy1.originalMethod(12));

        System.out.println("\nClass with enable = true");
        OriginalEnabled originalEnabled = new OriginalEnabled();
        //Создаем прокси нашего объекта original
        SomeInterface originalProxy2 = createProxy(originalEnabled);
        //Вызываем у прокси объекта один из методов нашего оригинального объекта
        originalProxy2.originalMethod(87);
    }
}
