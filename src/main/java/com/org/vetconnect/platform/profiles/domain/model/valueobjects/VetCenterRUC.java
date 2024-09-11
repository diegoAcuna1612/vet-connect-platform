package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record VetCenterRUC(Long vetCenterRUC) {
    public VetCenterRUC() {
        this(null);
    }

    public VetCenterRUC {
        if (vetCenterRUC == null) {
            throw new IllegalArgumentException("RUC cannot be null");
        }
        if (vetCenterRUC.toString().length() != 11) {
            throw new IllegalArgumentException("RUC must have 11 digits");
        }
    }


}
