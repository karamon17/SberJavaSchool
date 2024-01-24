package org.example.task4;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {
        constantChecker(Task4.class);
    }

    /**
     * Метод проверяет, что все String константы имеют значение, равное их имени
     * @param clazz класс, в котором необходимо проверить константы
     */
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
