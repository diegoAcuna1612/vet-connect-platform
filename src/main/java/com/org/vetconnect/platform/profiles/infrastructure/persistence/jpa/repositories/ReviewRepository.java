package com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findReviewByVetCenterId(Long vetCenterId);
    List<Review> findReviewByPetOwnerId(Long petOwnerId);
}
