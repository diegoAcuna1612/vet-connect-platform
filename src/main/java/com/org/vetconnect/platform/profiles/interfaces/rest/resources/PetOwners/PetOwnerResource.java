package com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners;

public record PetOwnerResource(
        Long id,
        String name,
        String email,
        Long dni,
        Long phone,
        String photo
) {
}
