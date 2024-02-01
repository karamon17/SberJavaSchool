package org.example;

public class Main {
    public static void main(String[] args) {
        ParsingFile parsingFile = new ParsingFileImpl();

        String path = System.getProperty("user.dir") + "/HomeWork10/src/main/resources/numbers.txt";
        String string = null;
        try {
            string = parsingFile.fileToString(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] integerArray = parsingFile.stringToIntegerArray(string);

        byte count = 1;
        for (Integer integer : integerArray) {
            Thread thread = new Thread(new FactorialCalculation(integer, count));
            count++;
            thread.start();
        }
    }
}
