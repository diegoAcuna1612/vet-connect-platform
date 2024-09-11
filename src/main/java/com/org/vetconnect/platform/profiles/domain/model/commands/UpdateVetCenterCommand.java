package com.org.vetconnect.platform.profiles.domain.model.commands;

public record UpdateVetCenterCommand(
        Long id,
        String name,
        String email,
        Long ruc,
        Long phone,
        String imageProfile,
        String description
) {
}
