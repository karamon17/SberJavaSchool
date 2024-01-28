package org.example.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CacheProxy {

    /**
     * Creates a proxy object for the specified service
     * @param service - service to create a proxy for
     * @param <T> - type of service
     * @return - proxy object
     */
    public <T> T cache(T service) {
        ClassLoader classLoader = service.getClass().getClassLoader();
        Class<?>[] interfaces = service.getClass().getInterfaces();

        InvocationHandler handler = new CacheInvocationHandler(service);

        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
