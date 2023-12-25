import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.TreeSet;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeSet<String> treeStr = new TreeSet<>();
        treeStr.add("lena");
        treeStr.add("petya");

        // Используем свой компаратор для сортировки в обратном порядке

        // Передаем компаратор в конструктор TreeSet
        TreeSet<Person> treePerson = new TreeSet<>((person1, person2) -> person1.getName().compareTo(person2.getName()));

        // Добавляем элементы в TreeSet
        treePerson.add(new Person("Alice"));
        treePerson.add(new Person("Bob"));
        treePerson.add(new Person("Charlie"));

        // Вывод элементов в порядке, определенном компаратором
        for (Person person : treePerson) {
            System.out.println(person.name);
        }
    }
}