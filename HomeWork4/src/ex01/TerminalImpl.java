package ex01;

public class TerminalImpl implements Terminal{
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(String correctPin) {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator(correctPin);
    }

    public void inputPinAndValidate() {
        pinValidator.validatePin();
    }

    @Override
    public void checkBalance() {
        if (!pinValidator.isAccountOpen()) {
            pinValidator.validatePin();
        }
        if (pinValidator.isAccountOpen()) {
            System.out.println("Баланс: " + server.getBalance());
        }
    }

    @Override
    public void put(int amount) {
        if (!pinValidator.isAccountOpen()) {
            pinValidator.validatePin();
        }
        if (pinValidator.isAccountOpen() && checkAmount(amount)) {
            server.put(amount);
        }
    }

    @Override
    public void withdraw(int amount) {
        if (!pinValidator.isAccountOpen()) {
            pinValidator.validatePin();
        }
        if (pinValidator.isAccountOpen() && checkAmount(amount)) {
            server.withdraw(amount);
        }
    }

    public static boolean checkAmount(int amount) {
        if (amount % 100 == 0 && amount > 0) {
            return true;
        }
        else if (amount < 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        else {
            throw new IllegalArgumentException("Сумма должна быть кратна 100");
        }
    }
}
