package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

public record PetOwnerEmail(
    @jakarta.validation.constraints.Email
    String email
) {
    public PetOwnerEmail() {
        this(null);
    }

    public PetOwnerEmail {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }

}
