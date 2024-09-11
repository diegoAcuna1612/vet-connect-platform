package com.org.vetconnect.platform.appointments.domain.model.aggregates;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.BookingDetails;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.PetOwnerId;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.VetCenterId;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // para usar created_at y updated_at
@Entity
public class Booking extends AuditableAbstractAggregateRoot<Booking> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Embedded
    @Getter
    private PetOwnerId petOwnerId;

    @Embedded
    @Getter
    private VetCenterId vetCenterId;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ServiceType serviceType;

    @Embedded
    @Getter
    @Setter
    private BookingDetails bookingDetails;

    @Getter
    @Setter
    private Double price;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDateTime date;

    public Booking(PetOwnerId petOwner, VetCenterId vetCenter, ServiceType serviceType, BookingDetails bookingDetails, Double price, LocalDateTime date) {
        this.petOwnerId = petOwner;
        this.vetCenterId = vetCenter;
        this.serviceType = serviceType;
        this.bookingDetails = bookingDetails;
        this.price = price;
        this.date = date;
    }

    public Booking() {
    }

}
