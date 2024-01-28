package org.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceImpl implements Service {
    @Override
    public List<String> run(String item, Double value, LocalDate date) {
        List<String> result = new ArrayList<>();
        result.add(item + "_" + value + "_" + date.toString());
        return result;
    }

    @Override
    public List<String> work(String item) {
        List<String> result = new ArrayList<>();
        result.add(item);
        return result;
    }
}
