package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.Gym;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.GymRepository;
import dev.sparfinder.fal.request.CreateGymEntity;
import dev.sparfinder.fal.response.CoachGymViewResponse;
import dev.sparfinder.fal.response.CoachViewGymNameResponse;
import dev.sparfinder.fal.util.helper.ImageCompressor;
import dev.sparfinder.fal.util.helper.OauthUsernameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    BucketService bucketService;

    @Value("${aws.s3.url}")
    private String s3Url;


    public ResponseEntity<CreateGymEntity> createGym(OAuth2User principal, CreateGymEntity gym) throws IOException {
        String id = OauthUsernameHelper.getId(principal);
        Coach coach = coachRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (coach.getGym() != null || coach.isOwner()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has a gym");
        }

        gymRepository.findByNameAndAddress(gym.getName(), gym.getLocation())
                .ifPresent(g -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gym already exists");
                });

        MultipartFile profilePic = gym.getProfilePic();
        MultipartFile compressedProfilePic = ImageCompressor.compressImage(profilePic);
        String key = "gym-profile/" + id + "/" + coach.getId();
        bucketService.putObjectIntoBucket(compressedProfilePic, key);

        Gym newGym = new Gym();
        newGym.setName(gym.getName());
        List<Coach> coaches = new ArrayList<Coach>();
        coaches.add(coach);
        newGym.setCoaches(coaches);
        newGym.setProfilePic(s3Url+key);
        newGym.setRules(gym.getRules());
        newGym.setAddress(gym.getLocation());
        newGym.setCountry(gym.getCountry());
        gymRepository.save(newGym);

        coach.setGym(newGym);
        coach.setOwner(true);
        coachRepository.save(coach);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<CoachViewGymNameResponse> viewCoachGymName(OAuth2User principal) {
        String id = OauthUsernameHelper.getId(principal);
        Coach coach = coachRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!coach.isOwner()) {
            return ResponseEntity.badRequest().build();
        }

        String gymName = coach.getGym().getName();
        return ResponseEntity.ok(new CoachViewGymNameResponse(gymName));

    }

    public ResponseEntity<CoachGymViewResponse> viewCoachGym(OAuth2User principal) {
        String id = OauthUsernameHelper.getId(principal);
        Coach coach = coachRepository.findByUserId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (coach.getGym() == null || !coach.isOwner()) {
            return ResponseEntity.badRequest().build();
        }

        Gym gym = coach.getGym();
        return ResponseEntity.ok(new CoachGymViewResponse(gym.getId(), gym.getName(), gym.getProfilePic(), gym.getRules(), gym.getAddress(), gym.getCountry()));
    }
}
