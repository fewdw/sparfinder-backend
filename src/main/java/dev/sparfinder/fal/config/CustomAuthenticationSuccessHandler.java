package dev.sparfinder.fal.config;

import dev.sparfinder.fal.entity.AccountType;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${frontend.url}")
    String frontendUrl;

    private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String id = (String) oAuth2User.getAttributes().get("sub");
        String email = (String) oAuth2User.getAttributes().get("email");
        String name = (String) oAuth2User.getAttributes().get("name");
        String picture = (String) oAuth2User.getAttributes().get("picture");

        if (!userService.userExists(id)) {
            User newUser = new User(id, email, name, picture, LocalDate.now(), AccountType.USER);
            userService.addUser(newUser);
        }

        String frontend = UriComponentsBuilder.fromUriString(frontendUrl).build().toUriString();
        response.sendRedirect(frontend);
    }


}
