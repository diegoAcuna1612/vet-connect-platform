package com.org.vetconnect.platform.appointments.domain.services;

import com.org.vetconnect.platform.appointments.domain.model.aggregates.Booking;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetAllBookingsByTypeServiceQuery;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingByIdQuery;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingsByTypeServiceInWeekQuery;

import java.util.List;
import java.util.Optional;

public interface BookingQueryService {
    Optional<Booking> handle(GetBookingByIdQuery query);
    List<Booking> handle(GetBookingsByTypeServiceInWeekQuery query);

}
