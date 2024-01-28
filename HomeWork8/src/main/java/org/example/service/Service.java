package org.example.service;

import org.example.annotation.Cache;
import java.time.LocalDate;
import java.util.List;

import static org.example.annotation.CacheType.FILE;
import static org.example.annotation.CacheType.IN_MEMORY;


public interface Service {

    /**
     * The method concatenates params and returns a list of strings
     * @param item - string
     * @param value - double
     * @param date - date
     * @return - list of strings
     */
    @Cache(cacheType = FILE, fileNamePrefix = "data", zip = true, identityBy = {String.class, Double.class})
    List<String> run(String item, Double value, LocalDate date);

    /**
     * The method returns a list of strings
     * @param item - string
     * @return - list of strings
     */
    @Cache(cacheType = IN_MEMORY,  memoryCacheSize = 100_000)
    List<String> work(String item);

}