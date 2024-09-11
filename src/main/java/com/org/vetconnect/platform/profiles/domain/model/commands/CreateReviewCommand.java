package com.org.vetconnect.platform.profiles.domain.model.commands;

// verificar correctamente con el CreateReviewResource, deben estar en el mismo orden
public record CreateReviewCommand(
        Long petOwnerId,
        Long vetCenterId,
        String comments,
        Integer rating
) {
}
