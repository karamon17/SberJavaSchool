package task6;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        if (number <= 1) {
            return 1;
        } else {
            return number * calc(number - 1);
        }
    }
}
