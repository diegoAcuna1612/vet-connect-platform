package com.org.vetconnect.platform.appointments.domain.model.queries;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;

public record GetAllBookingsByTypeServiceQuery(
        ServiceType serviceType
) {
}
