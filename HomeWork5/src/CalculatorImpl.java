public class CalculatorImpl implements Calculator {

    public String getName() {
        return name;
    }

    String name = "CalculatorImpl";
    public static final String MONDAY = "MONDAY";
    public static final String FRIDAY = "NOT FRIDAY";

    @Override
    public int calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        else if (number == 1 || number == 0) {
            return 1;
        }
        else {
            return number * calc(number - 1);
        }
    }

    private void privateMethod() {
        System.out.println("privateMethod");
    }
}
