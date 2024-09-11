package com.org.vetconnect.platform.appointments.interfaces.rest.transform;

import com.org.vetconnect.platform.appointments.domain.model.commands.DeleteBookingCommand;

public class DeleteBookingCommandFromResourceAssembler {
    public DeleteBookingCommand toCommandFromResource(Long id) {
        return new DeleteBookingCommand(id);
    }
}
