package com.fileStorage.fileStorage.user;

import com.fileStorage.fileStorage.fileStorage.SaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserService
{
    private final UserDataAcess userDataAcess;
    private final SaveFile saveFile;

    @Autowired
    public UserService (UserDataAcess a,SaveFile saveFile)
    {
        this.userDataAcess=a;
        this.saveFile=saveFile;
    }

    List<UserProfile> getUserProfiles()
    {
        return userDataAcess.getUserProfiles();
    }

    public void uploadProfileImage(UUID userId, MultipartFile file)
    {
        if(file.isEmpty()) throw new IllegalStateException("Empty File");

        if(!(file.getContentType()).startsWith("image")) throw new IllegalStateException("File not Image");

//        System.out.println(file.getContentType());

        UserProfile user= helperGetUser(userId);
        Map<String,String> metadata= new HashMap<>();
        metadata.put("Content-Type",file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));

        String str=file.getOriginalFilename();
        String uploadFileName=userId+str;
        try
        {
            user.setUserImage(uploadFileName);
            saveFile.save(uploadFileName,file.getInputStream(),metadata);
        }

        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadProfileImage(UUID userId)
    {
        UserProfile user=helperGetUser(userId);
        if(user==null) return new byte[0];
//        String blobName= String.valueOf(user.getUserImage());
//        System.out.println("download "+blobName);

        return user.getUserImage()
                .map(key -> saveFile.download(key))
                .orElse(new byte[0]);
    }

    public UserProfile helperGetUser(UUID userId)
    {
        UserProfile user= userDataAcess
                .getUserProfiles()
                .stream()
                .filter(userProfile->userProfile.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("User profile "+userId+" not found"));
        return user;
    }
}
