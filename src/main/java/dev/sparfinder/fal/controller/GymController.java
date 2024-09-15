package dev.sparfinder.fal.controller;

import dev.sparfinder.fal.request.CreateGymEntity;
import dev.sparfinder.fal.service.GymService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CreateGymEntity> createGym(@AuthenticationPrincipal OAuth2User principal, @RequestBody @Valid CreateGymEntity gym) {
        try {
            return gymService.createGym(principal, gym);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
