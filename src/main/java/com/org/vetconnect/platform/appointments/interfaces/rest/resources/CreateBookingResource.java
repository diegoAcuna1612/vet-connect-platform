package com.org.vetconnect.platform.appointments.interfaces.rest.resources;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.BookingDetails;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;

import java.time.LocalDateTime;

public record CreateBookingResource(
        Long petOwnerId,
        Long vetCenterId,
        ServiceType serviceType,
        BookingDetails bookingDetails,
        Double price,
        LocalDateTime bookingDate
) {
}
