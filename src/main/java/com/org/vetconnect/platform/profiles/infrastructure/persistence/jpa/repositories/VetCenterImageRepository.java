package com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories;

import com.org.vetconnect.platform.profiles.domain.model.entities.VetCenterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VetCenterImageRepository extends JpaRepository<VetCenterImage,Long> {
    Optional<VetCenterImage> findVetCenterImageById(Long vetCenterImageId);
}