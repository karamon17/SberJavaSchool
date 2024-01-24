package org.example.task6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class PerformanceProxy implements InvocationHandler {
    private final Object target;

    public PerformanceProxy(Object target) {
        this.target = target;
    }

    /**
     * Создает прокси-объект для класса, реализующего интерфейс interfaceClass
     * @param target объект, для которого создается прокси
     * @param interfaceClass интерфейс, который реализует класс target
     * @param <T> тип интерфейса
     * @return прокси-объект
     */
    public static <T> T createProxy(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new PerformanceProxy(target)
        );
    }

    /**
     * Вызывается при вызове метода у прокси-объекта
     * @param proxy прокси-объект
     * @param method метод, который вызывается у прокси-объекта
     * @param args аргументы метода
     * @return результат работы метода
     * @throws Throwable исключение, которое может быть брошено методом
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        try {
            return method.invoke(target, args);
        } finally {
            long endTime = System.nanoTime();
            if (method.isAnnotationPresent(Metric.class)) {
                System.out.println("Время работы метода: " + (endTime - startTime) + " наносек.");
            }
        }
    }
}