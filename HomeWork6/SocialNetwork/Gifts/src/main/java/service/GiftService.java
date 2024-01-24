package service;

import domain.Gift;
import domain.Profile;

import java.util.List;

public interface GiftService {

    /**
     * Отправляет подарок
     * @param from профиль пользователя
     * @param to профиль друга
     * @param gift объект класса Gift
     * @return true, если подарок успешно отправлен
     */
    Boolean sendGift(Profile from, Profile to, Gift gift);

    /**
     * Возвращает количество подарков
     * @param profile профиль пользователя
     * @return количество подарков
     */
    Integer amountGifts(Profile profile);

    /**
     * Возвращает список подарков
     * @param profile профиль пользователя
     * @return список подарков
     */
    List<Gift> showGifts(Profile profile);

    /**
     * Удаляет подарок
     * @param profile профиль пользователя
     * @param gift объект класса Gift
     * @return true, если подарок успешно удален
     */
    Boolean deleteGift(Profile profile, Gift gift);
}
