package org.example;

import java.util.List;

public interface Source {
    /**
     * Save data to the source
     * @param key - key to save
     * @param result - data to save
     */
    void saveData(String key, List<Integer> result);
    /**
     * Get data from the source
     * @param key - key to get
     * @return - data from the source
     */
    List<Integer> getData(String key);
}
