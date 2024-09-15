package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.GymRepository;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.request.CreateGymEntity;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    CoachRepository coachRepository;

    public ResponseEntity<CreateGymEntity> createGym(OAuth2User principal, CreateGymEntity gym) {
        String id = OauthUsernameHelper.getId(principal);
        Coach coach = coachRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if ( coach.getGym() != null || coach.isOwner()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has a gym");
        }

        // ADD PROFILE PIC TO s3 BUCKET
        /*
        Add gym to coach, add coach to gym, create and save gym and other entities.
         */
        continue here....

        return ResponseEntity.ok(gym);
    }
}
