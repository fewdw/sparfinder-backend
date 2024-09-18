package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.GymRepository;
import dev.sparfinder.fal.request.CreateGymEntity;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    BucketService bucketService;


    public ResponseEntity<CreateGymEntity> createGym(OAuth2User principal, CreateGymEntity gym) throws IOException {
        String id = OauthUsernameHelper.getId(principal);
        Coach coach = coachRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (coach.getGym() != null || coach.isOwner()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has a gym");
        }

        // Upload profile picture to S3
        MultipartFile profilePic = gym.getProfilePic();
        String key = "profile-pics/" + id + "/" + profilePic.getOriginalFilename();
        bucketService.putObjectIntoBucket(profilePic, key);

        // COMPRESS IMAGE
        // SAVE COACH, GYM ETC...
        //CHANGE BUCKET FOLDER STUFF....


        // Continue with your logic
        return ResponseEntity.ok().build();
    }

}
