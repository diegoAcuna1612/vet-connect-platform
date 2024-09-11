package com.org.vetconnect.platform.profiles.domain.model.commands;

public record CreatePetOwnerCommand(
        String name,
        String email,
        Long dni,
        Long phone,
        String photo
) {
}
