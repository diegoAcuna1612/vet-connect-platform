package com.org.vetconnect.platform.profiles.application.queryServices;

import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllReviewByVetCenterIdQuery;
import com.org.vetconnect.platform.profiles.domain.services.ReviewQueryService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewByVetCenterIdQuery query) {
        return reviewRepository.findReviewByVetCenterId(query.vetCenterId());
    }
}
