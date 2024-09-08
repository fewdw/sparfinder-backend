package dev.sparfinder.fal.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserNameRequestHelper {

    public static String getCurrentUsername() {
        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAttribute("name");
    }
}
