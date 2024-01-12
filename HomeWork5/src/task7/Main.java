package task7;

public class Main {
    public static void main(String[] args) {
        PersonFrom personFrom = new PersonFrom("John Doe", 25);
        PersonTo personTo = new PersonTo();

        BeanUtils.assign(personTo, personFrom);

        System.out.println("PersonTo fullName: " + personTo.getFullName());
        System.out.println("PersonTo age: " + personTo.getAge());
    }
}
