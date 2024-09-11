package com.org.vetconnect.platform.appointments.domain.model.queries;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;

import java.time.LocalDateTime;

public record GetBookingsByTypeServiceInWeekQuery(
        ServiceType serviceType,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime
) {
}
