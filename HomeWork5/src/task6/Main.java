package task6;

public class Main {
    public static void main(String[] args) {
        Calculator realCalculator = new CalculatorImpl();

        Calculator calculator = PerformanceProxy.createProxy(realCalculator, Calculator.class);

        System.out.println(calculator.calc(5));
    }
}
