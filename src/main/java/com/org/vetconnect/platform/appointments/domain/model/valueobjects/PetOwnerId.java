package com.org.vetconnect.platform.appointments.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PetOwnerId(Long petOwnerId) {
    public PetOwnerId {
        if (petOwnerId < 0) {
            throw new IllegalArgumentException("PetOwner petOwnerId cannot be negative");
        }
    }
    public PetOwnerId() {
        this(0L);
    }
}
