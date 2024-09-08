package dev.sparfinder.fal.repository;

import dev.sparfinder.fal.entity.Boxer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxerRepository extends JpaRepository<Boxer, Long> {

    Optional<Boxer> findByUserId(String userId);
}
