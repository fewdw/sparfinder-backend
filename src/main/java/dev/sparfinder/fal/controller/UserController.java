package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.response.CreateCoachProfileResponse;
import dev.sparfinder.fal.service.UserService;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("info")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUserById(OauthUsernameHelper.getId(principal));
    }

//    @PostMapping("profile/coach")
//    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(@AuthenticationPrincipal OAuth2User principal) {
//        return userService.createCoachProfile(OauthUsernameHelper.getEmail(principal));
//    }
//
//    @PostMapping("profile/boxer")
//    public String createBoxerProfile(@AuthenticationPrincipal OAuth2User principal, @RequestBody) {
//        return userService.createBoxerProfile(OauthUsernameHelper.getEmail(principal), );
//    }

}
