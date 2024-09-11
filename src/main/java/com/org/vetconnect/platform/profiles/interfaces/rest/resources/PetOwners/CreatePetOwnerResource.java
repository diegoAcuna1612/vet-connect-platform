package com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners;

public record CreatePetOwnerResource(
        String name,
        String email,
        Long dni,
        Long phone,
        String photo
) {
}
