package service;

import domain.Profile;

import java.util.Set;

public interface FriendsService {

    /**
     * Добавляет друга
     * @param profile1 профиль пользователя
     * @param profile2 профиль друга
     * @return true, если друг успешно добавлен
     */
    boolean addFriend(Profile profile1, Profile profile2);

    /**
     * Возвращает список друзей
     * @param profile профиль пользователя
     * @return список друзей
     */
    Set<Profile> getFriends(Profile profile);

    /**
     * Удаляет друга
     * @param profile1 профиль пользователя
     * @param profile2 профиль друга
     * @return true, если друг успешно удален
     */
    boolean deleteFriend(Profile profile1, Profile profile2);

    /**
     * Возвращает количество друзей
     * @param profile профиль пользователя
     * @return количество друзей
     */
    Integer amountFriends(Profile profile);

    /**
     * Проверяет, являются ли пользователи друзьями
     * @param profile1 профиль пользователя
     * @param profile2 профиль друга
     * @return true, если пользователи являются друзьями
     */
    Boolean isFriends(Profile profile1, Profile profile2);
}
