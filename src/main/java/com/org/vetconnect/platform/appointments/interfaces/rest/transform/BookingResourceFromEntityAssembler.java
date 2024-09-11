package com.org.vetconnect.platform.appointments.interfaces.rest.transform;

import com.org.vetconnect.platform.appointments.domain.model.aggregates.Booking;
import com.org.vetconnect.platform.appointments.interfaces.rest.resources.BookingResource;
import org.springframework.stereotype.Component;

@Component
public class BookingResourceFromEntityAssembler {
    public BookingResource toResourceFromEntity(Booking booking) {
        return new BookingResource(
                booking.getId(),
                booking.getPetOwnerId().petOwnerId(),
                booking.getVetCenterId().vetCenterId(),
                booking.getServiceType(),
                booking.getBookingDetails(),
                booking.getPrice(),
                booking.getDate(),
                booking.getDate()
        );
    }
}
