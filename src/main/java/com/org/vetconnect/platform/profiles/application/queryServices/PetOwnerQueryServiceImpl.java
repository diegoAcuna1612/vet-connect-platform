package com.org.vetconnect.platform.profiles.application.queryServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllPetOwnersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetPetOwnerByIdQuery;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerQueryService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.PetOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetOwnerQueryServiceImpl implements PetOwnerQueryService {

    private final PetOwnerRepository petOwnerRepository;

    public PetOwnerQueryServiceImpl(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    @Override
    public Optional<PetOwner> handle(GetPetOwnerByIdQuery query) {
        return petOwnerRepository.findById(query.id());
    }

    @Override
    public List<PetOwner> handle(GetAllPetOwnersQuery query) {
        return petOwnerRepository.findAll();
    }
}