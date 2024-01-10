package ex01;

public class TerminalImpl implements Terminal{
    private final TerminalServer server;
    private final PinValidator pinValidator;

    public TerminalImpl(String correctPin) {
        this.server = new TerminalServer();
        this.pinValidator = new PinValidator(correctPin);
    }

    public PinValidator getPinValidator() {
        return pinValidator;
    }

    public void inputPinAndValidate() {
        pinValidator.validatePin();
    }

    @Override
    public void checkBalance() {
        System.out.println("Баланс: " + server.getBalance());
    }

    @Override
    public void put(int amount) {
        try {
            checkAmount(amount);
            server.put(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void withdraw(int amount) {
        try {
            checkAmount(amount);
            server.withdraw(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
