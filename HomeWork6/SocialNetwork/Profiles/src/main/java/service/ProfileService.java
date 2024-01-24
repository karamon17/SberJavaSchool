package service;

import domain.Profile;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.Set;

public interface ProfileService {

    /**
     * Возвращает список друзей пользователя
     * @param user профиль пользователя
     * @return список друзей
     */
    Set<Profile> getFriends(Profile user);

    /**
     * Создает и возвращает новый профиль пользователя
     * @param email email пользователя
     * @param password пароль пользователя
     * @param firstname имя пользователя
     * @param lastname фамилия пользователя
     * @param gender пол пользователя
     * @param birthday дата рождения пользователя
     * @throws UserAlreadyExistsException если пользователь с таким email уже существует
     * @return новый профиль пользователя
     */
    Profile register(String email, String password, String firstname, String lastname, String gender, LocalDate birthday) throws UserAlreadyExistsException;

    /**
     * Возвращает профиль пользователя по email
     * @param email email пользователя
     * @param password пароль пользователя
     * @throws UserNotFoundException если пользователь не найден
     * @return профиль пользователя
     */
    Profile login(String email, String password) throws UserNotFoundException;

    /**
     * Удаляет профиль пользователя
     * @param user профиль пользователя
     */
    void deleteAccount(Profile user);
}
