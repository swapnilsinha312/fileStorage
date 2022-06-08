package com.fileStorage.fileStorage.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService a)
    {
        this.userService=a;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles()
    {
        return userService.getUserProfiles();
    }

    @PostMapping(
            path="{userId}/image/upload",
            consumes= MediaType.MULTIPART_FORM_DATA_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE
        )

    public void uploadProfileImage(@PathVariable("userId") UUID userId ,
                                    @RequestParam("file") MultipartFile file)
    {
        System.out.println("Param received");
        userService.uploadProfileImage(userId,file);
    }

    @GetMapping("{userId}/image/download")
    public byte[] downloadProfileImage(@PathVariable("userId") UUID userId)
    {
//        String
        System.out.println("Param received download "+userId);
        return userService.downloadProfileImage(userId);
    }
}
