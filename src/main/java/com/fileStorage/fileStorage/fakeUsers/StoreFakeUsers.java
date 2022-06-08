package com.fileStorage.fileStorage.fakeUsers;

import com.fileStorage.fileStorage.user.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StoreFakeUsers
{
    private static final List<UserProfile> USER_PROFILES= new ArrayList<>();
    static
    {
        USER_PROFILES.add(new UserProfile(UUID.fromString("3ba1a45d-639b-40d1-b4c8-11acb9ca377f"),"Name1","2bdfc156-374d-40bf-9361-1819644f0e25Name2"));
        USER_PROFILES.add(new UserProfile(UUID.fromString("2bdfc156-374d-40bf-9361-1819644f0e25"),"Name2","2bdfc156-374d-40bf-9361-1819644f0e25Name2"));
        USER_PROFILES.add(new UserProfile(UUID.fromString("88eced14-c7bb-4312-b8ba-be02e87cb75c"),"Name3","88eced14-c7bb-4312-b8ba-be02e87cb75cName2"));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"Name4",null));
//        USER_PROFILES.add(new UserProfile(UUID.randomUUID(),"Name5",null));
    }

    public List<UserProfile> getUserProfiles()
    {
        return USER_PROFILES;
    }

}
