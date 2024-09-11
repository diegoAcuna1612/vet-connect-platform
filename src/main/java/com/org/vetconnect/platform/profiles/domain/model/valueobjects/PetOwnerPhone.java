package com.org.vetconnect.platform.profiles.domain.model.valueobjects;

public record PetOwnerPhone(Long petOwnerPhone) {
    public PetOwnerPhone() {
        this(null);
    }

    // phone not null and phone length is 9
    public PetOwnerPhone(Long petOwnerPhone) {
        if (petOwnerPhone != null && petOwnerPhone.toString().length() == 9) {
            this.petOwnerPhone = petOwnerPhone;
        } else {
            throw new IllegalArgumentException("Phone must be 9 digits");
        }
    }

    public Long getPhone() {
        return petOwnerPhone;
    }
}
