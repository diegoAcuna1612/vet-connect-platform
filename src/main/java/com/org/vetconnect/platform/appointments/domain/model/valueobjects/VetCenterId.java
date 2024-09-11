package com.org.vetconnect.platform.appointments.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterId(Long vetCenterId) {
    public VetCenterId {
        if (vetCenterId < 0) {
            throw new IllegalArgumentException("VetCenter vetCenterId cannot be negative");
        }
    }
    public VetCenterId() {
        this(0L);
    }
}