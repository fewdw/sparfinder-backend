package dev.sparfinder.fal.service;

import dev.sparfinder.fal.response.CreateBoxerProfileResponse;
import dev.sparfinder.fal.entity.AccountType;
import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.BoxerRepository;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.request.CreateBoxerProfileRequest;
import dev.sparfinder.fal.response.CreateCoachProfileResponse;
import dev.sparfinder.fal.response.UserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private BoxerRepository boxerRepository;

    public boolean userExists(String sub){
        return userRepository.existsById(sub);
    }

    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    public ResponseEntity<UserInfoResponse> getUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        return ResponseEntity.ok(new UserInfoResponse(user.getId(), user.getEmail(), user.getName(), user.getUsername(), user.getProfilePic(), user.getAccountType()));
    }

    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();

        if (user.getAccountType() != AccountType.USER){
            return ResponseEntity.badRequest().build();
        }

        if(coachRepository.findByUserId(userId).isPresent()){
            return ResponseEntity.badRequest().build();
        }

        if(boxerRepository.findByUserId(userId).isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Coach coach = new Coach(user, user.getName(), user.getProfilePic());
        coachRepository.save(coach);
        user.setCoach(coach);
        user.setAccountType(AccountType.COACH);
        userRepository.save(user);

        return ResponseEntity.ok(new CreateCoachProfileResponse(coach.getId(), coach.getName(), AccountType.COACH));
    }

    public ResponseEntity<CreateBoxerProfileResponse> createBoxerProfile(String id, CreateBoxerProfileRequest boxer) {


    }
}
