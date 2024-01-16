package org.example.task5;

public class Main {
    public static void main(String[] args) {
        DataService realService = new DataServiceImpl();

        DataService cachingProxy = CachingProxy.createProxy(realService, DataService.class);

        long beforeCache = System.currentTimeMillis();
        System.out.println(cachingProxy.fetchData("key1")); // Данные будут загружены и закэшированы
        System.out.println(System.currentTimeMillis() - beforeCache);

        long afterCache = System.currentTimeMillis();
        System.out.println(cachingProxy.fetchData("key1")); // Данные будут взяты из кэша
        System.out.println(System.currentTimeMillis() - afterCache);
    }
}
