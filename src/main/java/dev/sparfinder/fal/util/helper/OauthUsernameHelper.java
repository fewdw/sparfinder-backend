package dev.sparfinder.fal.util.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class OauthUsernameHelper {

    public static String getEmail(OAuth2User principal) {

        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not Logged In");
        }

        Map<String, Object> attributes = principal.getAttributes();
        String email = (String) attributes.get("email");

        if (email == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username not found in request");
        }

        return email;
    }

}
