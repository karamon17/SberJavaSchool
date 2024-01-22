package repository;

import domain.Profile;

import java.util.Set;

public interface ProfileRepository {
    Profile addNewAccount(Profile user);
    Profile deleteAccount(Profile user);
    Set<Profile> getAll();
    Profile getById(Long id);
}
