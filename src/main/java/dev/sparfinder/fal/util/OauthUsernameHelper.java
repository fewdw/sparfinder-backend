package dev.sparfinder.fal.util;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class OauthUsernameHelper {

    public static String getUsernameFromRequest(OAuth2User principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not Logged In");
        }

        Map<String, Object> attributes = principal.getAttributes();
        String name = (String) attributes.get("email");

        if (name == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username not found in request");
        }

        return name;
    }
}
