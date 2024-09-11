package com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerDNI;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.PetOwnerEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

    Optional<PetOwner> findPetOwnerByPetOwnerDNI(PetOwnerDNI petOwnerDNI);
    Optional<PetOwner> findPetOwnerByPetOwnerEmail(PetOwnerEmail petOwnerEmail);
}
