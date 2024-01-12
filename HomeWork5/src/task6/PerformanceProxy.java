package task6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class PerformanceProxy implements InvocationHandler {
    private final Object target;

    public PerformanceProxy(Object target) {
        this.target = target;
    }

    public static <T> T createProxy(Object target, Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new PerformanceProxy(target)
        );
    }

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