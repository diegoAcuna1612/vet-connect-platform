package com.org.vetconnect.platform.appointments.domain.model.valueobjects;

import java.time.LocalDateTime;

public record BookingDetails(
        LocalDateTime appointmentDateTime,
        String additionalInfo
) {
}
