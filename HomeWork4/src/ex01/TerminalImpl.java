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
    public void put(float amount) {
        if (!pinValidator.isAccountOpen()) {
            pinValidator.validatePin();
        }
        if (pinValidator.isAccountOpen()) {
            try {
                server.put(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void withdraw(float amount) {
        if (!pinValidator.isAccountOpen()) {
            pinValidator.validatePin();
        }
        if (pinValidator.isAccountOpen()) {
            try {
                server.withdraw(amount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
