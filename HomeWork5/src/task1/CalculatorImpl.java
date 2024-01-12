package task1;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        else if (number <= 1) {
            return 1;
        }
        else {
            return number * calc(number - 1);
        }
    }
}
