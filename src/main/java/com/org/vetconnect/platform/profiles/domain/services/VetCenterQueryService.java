package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.entities.VetCenterImage;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllVetCenterImagesByVetCenterIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllVetCentersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByNameQuery;

import java.util.List;
import java.util.Optional;

public interface VetCenterQueryService {

    Optional<VetCenter> handle(GetVetCenterByIdQuery query);
    Optional<VetCenter> handle(GetVetCenterByNameQuery query);
    List<VetCenter> handle(GetAllVetCentersQuery query);

    List<VetCenterImage> handle(GetAllVetCenterImagesByVetCenterIdQuery query);
}
