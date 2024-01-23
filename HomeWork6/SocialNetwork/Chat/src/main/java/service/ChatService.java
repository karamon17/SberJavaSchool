package service;

import domain.Message;
import domain.Profile;

public interface ChatService {
    Message createMessage(Profile from, Profile to, String text);
    void sendMessage(Message message);
    void deleteMessage(Message message);
    void updateMessage(Message message);
    boolean isReceived(Message message);
    boolean isRead(Message message);
}
