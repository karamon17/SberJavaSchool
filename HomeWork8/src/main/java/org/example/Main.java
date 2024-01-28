package org.example;

import org.example.cache.CacheInvocationHandler;
import org.example.service.Service;
import org.example.service.ServiceImpl;

import java.lang.reflect.Proxy;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Service service = new ServiceImpl();

        Service serviceProxy = (Service) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), service.getClass().getInterfaces(), new CacheInvocationHandler(service)
        );

        String[] tasks = {"singing", "meditating", "painting"};
        double[] durations = {1, 2};

        performTasks(serviceProxy, tasks, durations);
        performTasks(serviceProxy, tasks, durations);
    }

    private static void performTasks(Service serviceProxy, String[] tasks, double[] durations) {
        for (String task : tasks) {
            serviceProxy.work(task);
            for (double duration : durations) {
                serviceProxy.run(task, duration, LocalDate.now());
            }
        }
        System.out.println();
    }
}
