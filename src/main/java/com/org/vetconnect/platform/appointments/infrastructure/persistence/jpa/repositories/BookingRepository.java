package com.org.vetconnect.platform.appointments.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.appointments.domain.model.aggregates.Booking;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // query que retorna todas as reservas de um determinado tipo de serviço em una semana
    @Query("SELECT b FROM Booking b WHERE b.serviceType = :serviceType AND b.date >= :startDate AND b.date <= :endDate")
    List<Booking> findBookingsByServiceTypeInWeek(
            @Param("serviceType")ServiceType serviceType,
            @Param("startDate")LocalDateTime startDate,
            @Param("endDate")LocalDateTime endDate
            );
}
