package task3;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        printGetters(Child.class);
    }

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

    private static boolean isGetter(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is"))
                && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType());
    }
}
