package service;

import domain.Profile;

import java.util.Set;

public interface FriendsService {
    boolean addFriend(Profile profile1, Profile profile2);
    Set<Profile> getFriends(Profile profile);
    boolean deleteFriend(Profile profile1, Profile profile2);
    Integer amountFriends(Profile profile);
    Boolean isFriends(Profile profile1, Profile profile2);
}
