package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.response.CoachProfile;
import dev.sparfinder.fal.service.CoachService;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping(value = "profile", produces = "application/json")
    public ResponseEntity<CoachProfile> geOwnCoachProfile(@AuthenticationPrincipal OAuth2User principal) {
        try{
            return coachService.getOwnCoachProfile(OauthUsernameHelper.getId(principal));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
