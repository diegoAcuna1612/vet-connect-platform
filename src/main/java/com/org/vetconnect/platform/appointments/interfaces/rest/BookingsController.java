package com.org.vetconnect.platform.appointments.interfaces.rest;

import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingByIdQuery;
import com.org.vetconnect.platform.appointments.domain.model.queries.GetBookingsByTypeServiceInWeekQuery;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;
import com.org.vetconnect.platform.appointments.domain.services.BookingCommandService;
import com.org.vetconnect.platform.appointments.domain.services.BookingQueryService;
import com.org.vetconnect.platform.appointments.interfaces.rest.resources.BookingResource;
import com.org.vetconnect.platform.appointments.interfaces.rest.resources.CreateBookingResource;
import com.org.vetconnect.platform.appointments.interfaces.rest.transform.BookingResourceFromEntityAssembler;
import com.org.vetconnect.platform.appointments.interfaces.rest.transform.CreateBookingCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Bookings", description = "Bookings Management Endpoints")
public class BookingsController {

    private final BookingCommandService bookingCommandService;
    private final BookingQueryService bookingQueryService;

    private final CreateBookingCommandFromResourceAssembler createBookingCommandFromResourceAssembler;
    private final BookingResourceFromEntityAssembler bookingResourceFromEntityAssembler;

    public BookingsController(BookingCommandService bookingCommandService,
                              BookingQueryService bookingQueryService,
                              CreateBookingCommandFromResourceAssembler createBookingCommandFromResourceAssembler,
                              BookingResourceFromEntityAssembler bookingResourceFromEntityAssembler)
    {
        this.bookingCommandService = bookingCommandService;
        this.bookingQueryService = bookingQueryService;
        this.createBookingCommandFromResourceAssembler = createBookingCommandFromResourceAssembler;
        this.bookingResourceFromEntityAssembler = bookingResourceFromEntityAssembler;
    }

    // add booking
    @PostMapping
    public ResponseEntity<BookingResource> createBooking(@RequestBody CreateBookingResource resource){
        var createBookingCommand = createBookingCommandFromResourceAssembler.toCommandFromResource(resource);
        var bookingId = bookingCommandService.handle(createBookingCommand);

        if (bookingId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getBookingByIdQuery = new GetBookingByIdQuery(bookingId);
        var booking = bookingQueryService.handle(getBookingByIdQuery);

        if (booking.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        var bookingResource = bookingResourceFromEntityAssembler.toResourceFromEntity(booking.get());
        return new ResponseEntity<>(bookingResource, HttpStatus.CREATED);
    }

    // get booking by id
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResource> getBookingById(@PathVariable Long bookingId){
        var getBookingByIdQuery = new GetBookingByIdQuery(bookingId);
        var booking = bookingQueryService.handle(getBookingByIdQuery);

        if (booking.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var bookingResource = bookingResourceFromEntityAssembler.toResourceFromEntity(booking.get());
        return new ResponseEntity<>(bookingResource, HttpStatus.OK);
    }

    // Get bookings by type service in week
    @GetMapping("/type-service/{typeService}/week")
    public ResponseEntity<List<BookingResource>> getBookingsByTypeServiceInWeek(
            @PathVariable ServiceType typeService,
            @RequestParam("startDateTime") LocalDateTime startDateTime,
            @RequestParam("endDateTime") LocalDateTime endDateTime) {

        var getBookingsByTypeServiceInWeekQuery = new GetBookingsByTypeServiceInWeekQuery(typeService, startDateTime, endDateTime);
        var bookings = bookingQueryService.handle(getBookingsByTypeServiceInWeekQuery);

        var bookingResources = bookings.stream()
                .map(bookingResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new ResponseEntity<>(bookingResources, HttpStatus.OK);
    }
}
