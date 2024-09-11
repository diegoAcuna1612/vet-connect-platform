package com.org.vetconnect.platform.appointments.interfaces.rest.transform;

import com.org.vetconnect.platform.appointments.domain.model.commands.CreateBookingCommand;
import com.org.vetconnect.platform.appointments.interfaces.rest.resources.CreateBookingResource;
import org.springframework.stereotype.Component;

@Component
public class CreateBookingCommandFromResourceAssembler {
    public CreateBookingCommand toCommandFromResource(CreateBookingResource resource) {
        return new CreateBookingCommand(
                resource.petOwnerId(),
                resource.vetCenterId(),
                resource.serviceType(),
                resource.bookingDetails(),
                resource.price(),
                resource.bookingDate()
        );

    }
}
