package service;

import domain.Profile;

import java.awt.*;

public interface PhotoService {
    void uploadPhoto(Image image);
    void deletePhoto(Image image);
    void downloadPhoto(Image image);
    Boolean likePhoto(Image image, Profile profileWhosePhoto, Profile profileWhoLikesPhoto);
    Integer amountLikes(Image image);
}
