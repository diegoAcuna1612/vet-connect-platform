package com.org.vetconnect.platform.profiles.application.commandServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateReviewCommand;
import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import com.org.vetconnect.platform.profiles.domain.services.ReviewCommandService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final PetOwnerRepository petOwnerRepository;
    private final VetCenterRepository vetCenterRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, PetOwnerRepository petOwnerRepository, VetCenterRepository vetCenterRepository) {
        this.reviewRepository = reviewRepository;
        this.petOwnerRepository = petOwnerRepository;
        this.vetCenterRepository = vetCenterRepository;
    }


    @Override
    public Review handle(CreateReviewCommand command) {
        // validate if pet owner exists
        PetOwner petOwner = petOwnerRepository.findById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));

        // validate if vet center exists
        VetCenter vetCenter = vetCenterRepository.findById(command.vetCenterId())
                .orElseThrow(() -> new IllegalArgumentException("Vet center not found"));

        // create review
        Review review = new Review(
                petOwner,
                vetCenter,
                command.comments(),
                command.rating()
        );

        // save review
        reviewRepository.save(review);
        return review;
    }
}
