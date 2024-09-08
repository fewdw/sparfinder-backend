package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.request.CreateBoxerProfileRequest;
import dev.sparfinder.fal.response.CreateCoachProfileResponse;
import dev.sparfinder.fal.response.UserInfoResponse;
import dev.sparfinder.fal.service.UserService;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping(value = "info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        return userService.getUserById(OauthUsernameHelper.getId(principal));
    }

    @PostMapping(value = "profile/coach")
    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(@AuthenticationPrincipal OAuth2User principal) {
        return userService.createCoachProfile(OauthUsernameHelper.getId(principal));
    }

    @PostMapping("profile/boxer")
    public ResponseEntity<CreateBoxerProfileResponse> createBoxerProfile(@AuthenticationPrincipal OAuth2User principal, @RequestBody CreateBoxerProfileRequest request) {
        return userService.createBoxerProfile(OauthUsernameHelper.getId(principal), request);
    }

}
