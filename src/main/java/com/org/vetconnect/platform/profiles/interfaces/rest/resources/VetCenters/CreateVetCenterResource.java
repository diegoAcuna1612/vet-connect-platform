package com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters;

public record CreateVetCenterResource(
        String name,
        String email,
        Long ruc,
        Long phone,
        String imageProfile,
        String description
) {
}
