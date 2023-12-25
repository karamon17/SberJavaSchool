package ex02;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Ivanov", "89188501952");
        book.add("Ivanov", "89187771293");
        book.add("Balabanov", "89889001232");

        System.out.println("Ivanov: " + book.get("Ivanov"));
        System.out.println("Balabanov: " + book.get("Balabanov"));
    }
}