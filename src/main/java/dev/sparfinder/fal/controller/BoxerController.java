package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.response.BoxerProfile;
import dev.sparfinder.fal.service.BoxerService;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boxer")
public class BoxerController {

    @Autowired
    private BoxerService boxerService;

    @GetMapping(value =  "profile", produces = "application/json")
    public ResponseEntity<BoxerProfile> getOwnBoxerProfile(@AuthenticationPrincipal OAuth2User principal) {
        try{
            return boxerService.getOwnBoxerProfile(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
