package service;

import domain.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    private ProfileService profileService;

    @Test
    void testGetFriendsWithEmptyProfileExpectEmptySet() {
        assertEquals(new HashSet<>(), profileService.getFriends(new Profile()));
    }

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    @Test
    void deleteAccount() {
    }
}