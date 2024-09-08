package dev.sparfinder.fal.service;

import dev.sparfinder.fal.entity.User;
import dev.sparfinder.fal.repository.BoxerRepository;
import dev.sparfinder.fal.repository.CoachRepository;
import dev.sparfinder.fal.repository.UserRepository;
import dev.sparfinder.fal.response.CreateCoachProfileResponse;
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

    public ResponseEntity<User> getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

//    public ResponseEntity<CreateCoachProfileResponse> createCoachProfile(String email) {
//        if ()
//        // if not a coach profile
//        // if not a boxer profile
//        // create it
//    }
}
