package com.org.vetconnect.platform.appointments.domain.services;

import com.org.vetconnect.platform.appointments.domain.model.commands.CreateBookingCommand;
import com.org.vetconnect.platform.appointments.domain.model.commands.DeleteBookingCommand;

public interface BookingCommandService {
    Long handle(CreateBookingCommand command);
    Long handle(DeleteBookingCommand command);
}
