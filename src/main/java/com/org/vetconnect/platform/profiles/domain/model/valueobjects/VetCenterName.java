package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterName(String vetCenterName) {
    public VetCenterName() {
        this(null);
    }

    // name cannot be null or blank
    public VetCenterName {
        if (vetCenterName == null || vetCenterName.isBlank()) {
            throw new IllegalArgumentException("Vet center name cannot be null or blank");
        }
    }

    public String getFullName() {
        return vetCenterName;
    }
}
