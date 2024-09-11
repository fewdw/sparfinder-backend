package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.Boxer;
import dev.sparfinder.fal.response.*;
import dev.sparfinder.fal.entity.AccountType;
import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.BoxerRepository;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.request.CreateBoxerProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return ResponseEntity.ok(new UserInfoResponse(user.getId(), user.getEmail(), user.getName(), user.getProfilePic(), user.getAccountType()));
    }

    public boolean canCreateProfile(String userId, User user) {

        if(coachRepository.findByUserId(userId).isPresent() || boxerRepository.findByUserId(userId).isPresent() || user.getAccountType() != AccountType.USER){
            return false;
        }

        return true;
    }

    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(!canCreateProfile(userId, user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has a profile");
        }


        Coach coach = new Coach(user, user.getName(), user.getProfilePic());

        coachRepository.save(coach);
        user.setCoach(coach);
        user.setAccountType(AccountType.COACH);
        userRepository.save(user);

        return ResponseEntity.ok(new CreateCoachProfileResponse(
                coach.getId(),
                coach.getName(),
                AccountType.COACH)
        );
    }

    public ResponseEntity<CreateBoxerProfileResponse> createBoxerProfile(String userId, CreateBoxerProfileRequest boxerRequest) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(!canCreateProfile(userId, user)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already has a profile");
        }

        Boxer newBoxer = new Boxer(
                user,
                boxerRequest.getNumberOfFights(),
                boxerRequest.getWeightKg(),
                boxerRequest.getHeightCm(),
                user.getName(),
                boxerRequest.getGender(),
                boxerRequest.getBirthDate(),
                boxerRequest.getStance(),
                user.getProfilePic(),
                boxerRequest.getLevel(),
                boxerRequest.getCountry(),
                boxerRequest.getCity()
        );

        boxerRepository.save(newBoxer);
        user.setBoxer(newBoxer);
        user.setAccountType(AccountType.BOXER);
        userRepository.save(user);

        return ResponseEntity.ok(new CreateBoxerProfileResponse(newBoxer.getId(), newBoxer.getName(), AccountType.BOXER));
    }

    public ResponseEntity<CoachProfile> getOwnCoachProfile(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        System.out.print(user.getName() + " found.");

        if (user.getAccountType() != AccountType.COACH){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a coach");
        }
        System.out.print("returning " + user.getName() + " found.");
        return ResponseEntity.ok(new CoachProfile(
                user.getEmail(),
                user.getName(),
                user.getProfilePic(),
                user.getCreatedAt()
        ));
    }

    public ResponseEntity<BoxerProfile> getOwnBoxerProfile(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getAccountType() != AccountType.BOXER){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a boxer");
        }

        return ResponseEntity.ok(new BoxerProfile(
                user.getEmail(),
                user.getName(),
                user.getProfilePic(),
                user.getCreatedAt(),
                user.getBoxer().getNumberOfFights(),
                user.getBoxer().getWeightKg(),
                user.getBoxer().getHeightCm(),
                user.getBoxer().getBirthDate(),
                user.getBoxer().getStance(),
                user.getBoxer().getLevel(),
                user.getBoxer().getCountry(),
                user.getBoxer().getCity()
        ));
    }
}
