package com.fileStorage.fileStorage.user;

import com.fileStorage.fileStorage.fakeUsers.StoreFakeUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDataAcess {
    private final StoreFakeUsers storeFakeUsers;

    @Autowired
    public UserDataAcess(StoreFakeUsers a)
    {
        this.storeFakeUsers=a;
    }

    List<UserProfile> getUserProfiles()
    {
        return storeFakeUsers.getUserProfiles();
    }
}
