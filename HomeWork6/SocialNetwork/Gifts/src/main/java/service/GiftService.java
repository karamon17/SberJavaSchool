package service;

import domain.Gift;
import domain.Profile;

import java.util.List;

public interface GiftService {
    Boolean sendGift(Profile from, Profile to, Gift gift);
    Integer amountGifts(Profile profile);
    List<Gift> showGifts(Profile profile);
    Boolean deleteGift(Profile profile, Gift gift);
}
