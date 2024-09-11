package com.org.vetconnect.platform.appointments.domain.model.commands;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.BookingDetails;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;

import java.time.LocalDateTime;

public record CreateBookingCommand(
        Long petOwnerId,
        Long vetCenterId,
        ServiceType serviceType,
        BookingDetails bookingDetails,
        Double price,
        LocalDateTime bookingDate
) {
}
