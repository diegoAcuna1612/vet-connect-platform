package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllPetOwnersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetPetOwnerByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PetOwnerQueryService {
    Optional<PetOwner> handle(GetPetOwnerByIdQuery query);
    List<PetOwner> handle(GetAllPetOwnersQuery query);
}
