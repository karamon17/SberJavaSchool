package ex01;

import java.util.Scanner;

public class PinValidator {
    private final int MAX_ATTEMPTS = 3;
    private final int LOCK_DURATION_SECONDS = 10;
    private final String correctPin;

    public PinValidator(String correctPin) {
        this.correctPin = correctPin;
    }
    public boolean isAccountOpen() {
        return isAccountOpen;
    }

    private boolean isAccountOpen = false;
    private int failedAttempts = 0;
    private long lockExpirationTime = 0;

    public void validatePin() {
        if (!isAccountOpen()) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder pinInput = new StringBuilder();
            try {
                while (true) {
                    if (isAccountLocked()) {
                        throw new AccountIsLockedException(getRemainingLockTime());
                    }
                    for (int i = 1; i <= 4; i++) {
                        boolean validInput = false;
                        while (!validInput) {
                            System.out.print("Введите цифру " + i + ": ");
                            String userInput = scanner.nextLine();

                            if (userInput.length() == 1 && Character.isDigit(userInput.charAt(0))) {
                                validInput = true;
                                pinInput.append(userInput);
                            } else {
                                System.out.println("Предупреждение! Введите корректную цифру.");
                            }
                        }
                    }
                    if (pinInput.toString().equals(correctPin)) {
                        System.out.println("Пин-код верен. Доступ разрешен.");
                        isAccountOpen = true;
                        break;
                    } else {
                        failedAttempts++;
                        System.out.println("Неверный пин-код. Осталось попыток: " + (MAX_ATTEMPTS - failedAttempts));
                        pinInput.setLength(0);
                        if (failedAttempts >= MAX_ATTEMPTS) {
                            lockAccount();
                            throw new AccountIsLockedException(getRemainingLockTime());
                        }
                    }
                }
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован на " + e.getRemainingLockTime() + " секунд");
            }
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
}
