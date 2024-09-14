package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.AccountType;
import dev.sparfinder.fal.entity.Coach;
import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.request.ChangeNameRequest;
import dev.sparfinder.fal.response.BoxerProfile;
import dev.sparfinder.fal.response.CoachProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CoachService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoachRepository coachRepository;

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


    public ResponseEntity<ChangeNameRequest> changeName(String id, ChangeNameRequest name) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Coach coach = coachRepository.findByUser(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Coach not found"));
        user.setName(name.getName());
        userRepository.save(user);
        coach.setName(name.getName());
        coachRepository.save(coach);
        return ResponseEntity.ok(name);
    }
}
