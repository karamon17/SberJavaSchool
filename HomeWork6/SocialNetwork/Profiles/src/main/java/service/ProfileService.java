package service;

import domain.Profile;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.Set;

public interface ProfileService {
    public Set<Profile> getFriends(Profile user);
    void register(String email, String password, String firstname, String lastname, String gender, LocalDate birthday) throws UserAlreadyExistsException;
    void login(String email, String password) throws UserNotFoundException;
    void deleteAccount(Profile user);
}
