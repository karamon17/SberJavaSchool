package service;

import domain.Message;
import domain.Profile;

public interface ChatService {

    /**
     * Создает и возвращает объект класса Message
     * @param from отправитель
     * @param to получатель
     * @param text текст сообщения
     */
    Message createMessage(Profile from, Profile to, String text);

    /**
     * Отправляет сообщение
     * @param message объект класса Message
     */
    void sendMessage(Message message);

    /**
     * Удаляет сообщение
     * @param message объект класса Message
     */
    void deleteMessage(Message message);

    /**
     * Обновляет сообщение
     * @param message объект класса Message
     */
    void updateMessage(Message message);

    /**
     * Помечает сообщение как полученное
     * @param message объект класса Message
     * @return true, если сообщение успешно доставлено
     */
    boolean isReceived(Message message);

    /**
     * Помечает сообщение как прочитанное
     * @param message объект класса Message
     * @return true, если сообщение прочитано
     */
    boolean isRead(Message message);
}
