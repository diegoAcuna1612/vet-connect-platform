package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PetOwnerDNI(Long petOwnerDNI) {

    // dni no puede ser nulo, vacio, y tiene que ser un numero positivo de 8 digitos
    public PetOwnerDNI {
        if (petOwnerDNI == null || petOwnerDNI.toString().length() != 8 || petOwnerDNI < 0) {
            throw new IllegalArgumentException("Pet owner DNI cannot be null, empty, or less than 0, and must be 8 digits long.");
        }
    }

    public Long getDNI(){
        return petOwnerDNI;
    }
}
