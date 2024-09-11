package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.request.CreateBoxerProfileRequest;
import dev.sparfinder.fal.response.*;
import dev.sparfinder.fal.service.UserService;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        try{
            return userService.getUserById(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // COACH PROFILE ROUTES

    @GetMapping(value = "profile/coach")
    public ResponseEntity<CoachProfile> geOwnCoachProfile(@AuthenticationPrincipal OAuth2User principal) {
        try{
            return userService.getOwnCoachProfile(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "profile/coach")
    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(@AuthenticationPrincipal OAuth2User principal) {
        try {
            return userService.createCoachProfile(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // BOXER PROFILE ROUTES

    @GetMapping("profile/boxer")
    public ResponseEntity<BoxerProfile> getOwnBoxerProfile(@AuthenticationPrincipal OAuth2User principal) {
        try{
            return userService.getOwnBoxerProfile(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("profile/boxer")
    public ResponseEntity<CreateBoxerProfileResponse> createBoxerProfile(@AuthenticationPrincipal OAuth2User principal, @RequestBody @Valid CreateBoxerProfileRequest boxer) {
        try{
            return userService.createBoxerProfile(OauthUsernameHelper.getId(principal), boxer);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
