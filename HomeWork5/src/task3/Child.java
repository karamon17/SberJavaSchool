package task3;

public class Child extends Parent {
    private String name;
    public int age;

    {
        name = "Child";
        age = 18;
    }

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void print() {
        System.out.println("Child");
    }

    private void printPrivate() {
        System.out.println("Child private");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return age >= 18;
    }
    
}
