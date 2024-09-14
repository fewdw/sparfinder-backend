package dev.sparfinder.fal.service;

import dev.sparfinder.fal.repository.GymRepository;
import dev.sparfinder.fal.response.CreateGymEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    public ResponseEntity<CreateGymEntity> createGym(OAuth2User principal, CreateGymEntity gym) {

        // check if gym doesn't already exist.
        // check if principal is a coach
        // create gym




        return ResponseEntity.ok(gym);
    }
}
