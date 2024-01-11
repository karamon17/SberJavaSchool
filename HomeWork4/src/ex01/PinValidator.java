package ex01;

import java.util.Scanner;

public class PinValidator {
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_DURATION_SECONDS = 10;
    private final String correctPin;
    private boolean isAccountOpen = false;
    private int failedAttempts = 0;
    private long lockExpirationTime = 0;

    private static final MessageDisplay display = new ConsoleDisplay();

    public PinValidator(String correctPin) {
        this.correctPin = correctPin;
    }

    public boolean isAccountOpen() {
        return isAccountOpen;
    }

    public void validatePin() {
        try {
            while (true) {
                StringBuilder pinInput = input();
                if (isAccountLocked()) {
                    throw new AccountIsLockedException(getRemainingLockTime());
                }
                if (pinInput.toString().equals(correctPin)) {
                    display.displayMessage("Пин-код верен. Доступ разрешен.");
                    isAccountOpen = true;
                    break;
                } else {
                    failedAttempts++;
                    display.displayMessage("Неверный пин-код. Осталось попыток: " + (MAX_ATTEMPTS - failedAttempts));
                    pinInput.setLength(0);
                    if (failedAttempts >= MAX_ATTEMPTS) {
                        lockAccount();
                        throw new AccountIsLockedException(getRemainingLockTime());
                    }
                }
            }
        } catch (AccountIsLockedException e) {
            display.displayMessage("Аккаунт заблокирован на " + e.getRemainingLockTime() + " секунд");
        }
    }

    private boolean isAccountLocked() {
        return System.currentTimeMillis() < lockExpirationTime;
    }

    private long getRemainingLockTime() {
        return (lockExpirationTime - System.currentTimeMillis()) / 1000;
    }

    private void lockAccount() {
        failedAttempts = 0;
        lockExpirationTime = System.currentTimeMillis() + (LOCK_DURATION_SECONDS * 1000);
    }

    private static StringBuilder input() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder pinInput = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            boolean validInput = false;
            while (!validInput) {
                display.displayMessage("Введите цифру " + i + " пин-кода: ");
                String userInput = scanner.nextLine();
                if (userInput.length() == 1 && Character.isDigit(userInput.charAt(0))) {
                    validInput = true;
                    pinInput.append(userInput);
                } else {
                    display.displayMessage("Предупреждение! Введите корректную цифру.");
                }
            }
        }
        return pinInput;
    }
}
