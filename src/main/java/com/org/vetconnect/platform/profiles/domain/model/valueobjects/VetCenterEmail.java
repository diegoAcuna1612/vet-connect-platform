package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

public record VetCenterEmail(
    @jakarta.validation.constraints.Email
    String email
) {
    public VetCenterEmail() {
        this(null);
    }

    public VetCenterEmail {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
    }

}
