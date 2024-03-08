package AnnotationReflectionAPI.Annotation;

import jdk.jfr.Enabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * С помощью своей аннотации и класса java.lang.reflect.Proxy реализовать вывод в консоль
 * аргументов методов и возвращаемое значение.
 * Создать аннотацию, которая вешается на класс. В аннотации должно быть свойство enable
 * Для работы с классом Proxy нужно создать интерфейс и реализовать его в классе, на который
 * будет вешаться аннотация
 * В прокси класса реализуется логика по выводу в консоль. Если свойство аннотации enable = false,
 * то вывод в консоль не должен отрабатывать, либо не должен создаваться прокси
 */
@Target(ElementType.TYPE)
@Enabled
public @interface MyAnnotation {

}
