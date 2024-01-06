package ex01;

public interface Terminal {
    //Проверить состояние счета
    //Снять положить деньги
    void checkBalance();
    void put(float amount);
    void withdraw(float amount);
}
