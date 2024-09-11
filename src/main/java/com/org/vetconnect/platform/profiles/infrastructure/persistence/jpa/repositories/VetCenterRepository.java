package com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetCenterRepository extends JpaRepository<VetCenter, Long> {

    Optional<VetCenter> findVetCenterByVetCenterName(VetCenterName vetCenterName);
    Optional<VetCenter> findVetCenterByVetCenterRUC(VetCenterRUC vetCenterRUC);

}
