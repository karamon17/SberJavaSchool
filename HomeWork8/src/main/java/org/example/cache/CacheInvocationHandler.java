package org.example.cache;

import org.example.annotation.Cache;
import org.example.annotation.CacheType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache = new HashMap<>();

    public CacheInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);

        if (cacheAnnotation != null) {
            String cacheKey = generateCacheKey(method, args);

            if (cache.containsKey(cacheKey)) {
                System.out.println("Cache hit for key: " + cacheKey);
                return cache.get(cacheKey);
            }

            Object result = method.invoke(target, args);

            cache.put(cacheKey, result);

            applyCacheSettings(cacheAnnotation, cacheKey, result);

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    /**
     * Generates a key for the cache
     * @param method - method for which the key is generated
     * @param args - method arguments
     * @return - generated key
     */
    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder();

        keyBuilder.append(method.getName());

        for (Object arg : args) {
            keyBuilder.append("_").append(arg);
        }

        return keyBuilder.toString();
    }

    /**
     * Applies cache settings
     * @param cacheAnnotation - cache annotation
     * @param cacheKey - cache key
     * @param result - method result
     */
    private void applyCacheSettings(Cache cacheAnnotation, String cacheKey, Object result) {
        if (cacheAnnotation.cacheType() == CacheType.FILE && cacheAnnotation.zip()) {
            saveToDiskWithCompression(cacheKey, result);
        }
    }

    /**
     * Saves the result to disk with compression
     * @param cacheKey - cache key
     * @param result - method result
     */
    private void saveToDiskWithCompression(String cacheKey, Object result) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new GZIPOutputStream(new FileOutputStream(cacheKey + ".gz")))) {
            oos.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}