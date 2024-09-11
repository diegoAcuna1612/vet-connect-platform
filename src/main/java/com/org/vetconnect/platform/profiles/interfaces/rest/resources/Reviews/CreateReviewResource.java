package com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews;

// record que recibe los datos necesarios para crear una reseña
public record CreateReviewResource(
        Long petOwnerId,
        Long vetCenterId,
        String comments,
        int rating
) {
}
