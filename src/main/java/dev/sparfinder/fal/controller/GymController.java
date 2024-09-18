package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.request.CreateGymEntity;
import dev.sparfinder.fal.service.GymService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateGymEntity> createGym(@AuthenticationPrincipal OAuth2User principal, @Valid @ModelAttribute CreateGymEntity gym) {
        try {
            return gymService.createGym(principal, gym);
        } catch (Exception e) {
            System.out.print(e);
            return ResponseEntity.badRequest().build();
        }
    }

}
