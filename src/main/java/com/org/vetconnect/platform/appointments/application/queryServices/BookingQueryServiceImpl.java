package com.org.vetconnect.platform.appointments.application.queryServices;

import com.org.vetconnect.platform.appointments.domain.model.aggregates.Booking;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingByIdQuery;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingsByTypeServiceInWeekQuery;
import com.org.vetconnect.platform.appointments.domain.services.BookingQueryService;
import com.org.vetconnect.platform.appointments.infrastructure.persistence.jpa.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingQueryServiceImpl implements BookingQueryService {

    private final BookingRepository bookingRepository;

    public BookingQueryServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // obtiene las reservas por id
    @Override
    public Optional<Booking> handle(GetBookingByIdQuery query) {
        return bookingRepository.findById(query.id());
    }

    // obtiene todas las reservas de un tipo de servicio en una semana
    @Override
    public List<Booking> handle(GetBookingsByTypeServiceInWeekQuery query) {
        return bookingRepository.findBookingsByServiceTypeInWeek(
                query.serviceType(),
                query.startDateTime(),
                query.endDateTime()
        );
    }
}
