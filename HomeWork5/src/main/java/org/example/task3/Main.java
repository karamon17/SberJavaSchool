package org.example.task3;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        printGetters(Child.class);
    }

    /**
     * Метод выводит на экран все геттеры класса и его родительских классов
     * @param clazz класс, в котором необходимо найти геттеры
     */
    private static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("Геттер: " + method.toString());
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            System.out.println("Геттеры родительского класса " + superClass.getName() + ":");
            printGetters(superClass);
        }
    }

    /**
     * Метод проверяет, является ли метод геттером
     * @param method метод, который необходимо проверить
     * @return true, если метод является геттером, иначе false
     */
    private static boolean isGetter(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType());
    }
}
