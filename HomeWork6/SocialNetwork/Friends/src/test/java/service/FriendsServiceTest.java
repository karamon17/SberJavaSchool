package service;

import domain.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FriendsServiceTest {

    @Mock
    FriendsService service;

    Profile profile1;
    Profile profile2;

    @BeforeEach
    void setup(){
        profile1 = new Profile();
        profile2 = new Profile();
    }

    @Test
    void addFriend() {

    }

    @Test
    void getFriends() {
    }

    @Test
    void deleteFriend() {
    }

    @Test
    void amountFriends() {
    }

    @Test
    void isFriends() {
    }
}