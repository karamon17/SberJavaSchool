import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        System.out.println("Задача 1: Расчет факториала числа: " + calculator.calc(5));
        Class<?> clazz = calculator.getClass();
        System.out.println("\nЗадача 2: Вывести на консоль все методы класса, включая все родительские методы " +
                "(включая приватные): \n" +  Arrays.toString(clazz.getDeclaredMethods()) + "\n" + Arrays.toString(clazz.getMethods()));
        System.out.println("\nЗадача 3: Вывести все геттеры класса:");
        printGetters(clazz);
        System.out.println("\nЗадача 4: Проверить что все String константы имеют значение = их имени: ");
        constantChecker(clazz);
    }

    private static void printGetters(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("Геттер: " + method.toString());
            }
        }

        // Рекурсивно вызываем для родительского класса, если таковой есть
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            System.out.println("Геттеры родительского класса " + superClass.getName() + ":");
            printGetters(superClass);
        }
    }

    private static boolean isGetter(Method method) {
        return method.getName().startsWith("get")
                && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType());
    }

    private static void constantChecker(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == String.class && java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                field.setAccessible(true);
                String value = null;
                try {
                    value = (String) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (field.getName().equals(value)) {
                    System.out.println("Поле " + field.getName() + " соответствует требованиям.");
                } else {
                    System.out.println("Поле " + field.getName() + " не соответствует требованиям.");
                }
            }
        }
    }
}