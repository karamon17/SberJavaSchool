package service;

import domain.Profile;

import java.awt.*;

public interface PhotoService {

    /**
     * Загружает фото
     * @param image объект класса Image
     */
    void uploadPhoto(Image image);

    /**
     * Удаляет фото
     * @param image объект класса Image
     */
    void deletePhoto(Image image);

    /**
     * Скачивает фото
     * @param image объект класса Image
     */
    void downloadPhoto(Image image);

    /**
     * Ставит или снимает лайк на фото
     * @param image объект класса Image
     * @param profileWhosePhoto профиль пользователя, которому принадлежит фото
     * @param profileWhoLikesPhoto профиль пользователя, который ставит или снимает лайк
     * @return true, если лайк успешно поставлен или снят
     */
    Boolean likePhoto(Image image, Profile profileWhosePhoto, Profile profileWhoLikesPhoto);

    /**
     * Возвращает количество лайков
     * @param image объект класса Image
     * @return количество лайков
     */
    Integer amountLikes(Image image);
}
