package com.fileStorage.fileStorage.user;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile
{
    private UUID userId;
    private String userName;
    private String userImage; //blob key

    public UserProfile(UUID userId, String userName, String userImage)
    {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<String> getUserImage() {
    return Optional.ofNullable(userImage);
}

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userImage, that.userImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userImage);
    }
}
