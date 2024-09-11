package com.org.vetconnect.platform.appointments.application.commandServices;

import com.org.vetconnect.platform.appointments.application.outboundServices.ExternalProfilesService;
import com.org.vetconnect.platform.appointments.domain.model.aggregates.Booking;
import com.org.vetconnect.platform.appointments.domain.model.commands.CreateBookingCommand;
import com.org.vetconnect.platform.appointments.domain.model.commands.DeleteBookingCommand;
import com.org.vetconnect.platform.appointments.domain.services.BookingCommandService;
import com.org.vetconnect.platform.appointments.infrastructure.persistence.jpa.repositories.BookingRepository;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingCommandServiceImpl implements BookingCommandService {

    private final BookingRepository bookingRepository;

    private final ExternalProfilesService externalProfilesService;

    public BookingCommandServiceImpl(BookingRepository bookingRepository,ExternalProfilesService externalProfilesService) {
        this.bookingRepository = bookingRepository;
        this.externalProfilesService = externalProfilesService;
    }


    @Override
    public Long handle(CreateBookingCommand command) {
        // validate if pet owner exists
        var petOwner = externalProfilesService.fetchPetOwnerIdById(command.petOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Pet owner not found"));

        // validate if vet center exists
        var vetCenter = externalProfilesService.fetchVetCenterIdById(command.vetCenterId())
                .orElseThrow(() -> new IllegalArgumentException("Vet center not found"));

        // create booking
        Booking booking = new Booking(
                petOwner,
                vetCenter,
                command.serviceType(),
                command.bookingDetails(),
                command.price(),
                command.bookingDate()
        );

        // save booking
        bookingRepository.save(booking);
        return booking.getId();
    }

    @Override
    public Long handle(DeleteBookingCommand command) {
        // validate if booking exists
        Booking booking = bookingRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        // delete booking
        bookingRepository.delete(booking);
        return booking.getId();
    }
}
