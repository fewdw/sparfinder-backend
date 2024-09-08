package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Home!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal OAuth2User principal) {

        return OauthUsernameHelper.getEmail(principal);
    }
}
