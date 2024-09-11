package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PetOwnerName(String petOwnerName) {
    public PetOwnerName() {
        this(null);
    }

    // name cannot be null or blank
    public PetOwnerName {
        if (petOwnerName == null || petOwnerName.isBlank()) {
            throw new IllegalArgumentException("Pet owner name cannot be null or blank");
        }
    }

    public String getFullName() {
        return petOwnerName;
    }
}
