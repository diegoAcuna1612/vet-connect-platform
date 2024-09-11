package com.org.vetconnect.platform.profiles.domain.model.commands;

public record CreateVetCenterImageCommand(
        Long vetCenterId,
        String imageUrl

) {
}
