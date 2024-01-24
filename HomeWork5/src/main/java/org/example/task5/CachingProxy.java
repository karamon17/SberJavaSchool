package org.example.task5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

class CachingProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, String> cache = new HashMap<>();

    private CachingProxy(Object target) {
        this.target = target;
    }

    /**
     * Создает прокси-объект для класса, реализующего интерфейс interfaceClass
     * @param target объект, для которого создается прокси
     * @param interfaceClass интерфейс, который реализует класс target
     * @param <T> тип интерфейса
     * @return прокси-объект
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new CachingProxy(target)
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
        String key = method.getName() + "_" + args[0];

        if (cache.containsKey(key)) {
            System.out.println("Returning cached data for key: " + key);
            return cache.get(key);
        } else {
            Object result = method.invoke(target, args);
            cache.put(key, (String) result);
            return result;
        }
    }
}