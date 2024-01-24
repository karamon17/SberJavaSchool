package org.example.task2;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        printMethods(Child.class);
    }

    /**
     * Метод выводит на экран все методы класса и его родительских классов
     * @param clazz класс, в котором необходимо найти методы
     */
    public static void printMethods(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("-------------------------------------\n" + clazz.getSimpleName() + " methods:\n");
        for (Method method : methods) {
            System.out.println("Метод: " + method.getName());
            System.out.println("Модификатор доступа: " + method.getModifiers());
            System.out.println("Тип возвращаемого значения: " + method.getReturnType());
            Class<?>[] parameterTypes = method.getParameterTypes();
            System.out.println("Параметры:");
            for (Class<?> parameterType : parameterTypes) {
                System.out.println("  " + parameterType.getName());
            }
            System.out.println();
        }
        if (clazz.getSuperclass() != null)
            printMethods(clazz.getSuperclass());
    }
}
