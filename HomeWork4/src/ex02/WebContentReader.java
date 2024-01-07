package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebContentReader {

    public static void main(String[] args) {
        while (true) {
            System.out.print("Введите URL ресурса (или 'exit' для выхода): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String input = reader.readLine();

                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Выход из программы.");
                    break;
                }

                readContent(input);
            } catch (IOException e) {
                System.out.println("Ошибка ввода-вывода: " + e.getMessage());
            }
        }
    }

    public static void readContent(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            System.out.println("Содержимое сайта " + urlString + ":\n");

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении содержимого сайта: " + e.getMessage());
        }
    }
}
