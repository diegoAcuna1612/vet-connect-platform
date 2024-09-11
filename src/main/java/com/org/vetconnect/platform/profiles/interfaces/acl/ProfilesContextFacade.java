package com.org.vetconnect.platform.profiles.interfaces.acl;

import com.org.vetconnect.platform.profiles.domain.model.queries.GetPetOwnerByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerCommandService;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerQueryService;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterCommandService;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final VetCenterCommandService vetCenterCommandService;
    private final VetCenterQueryService vetCenterQueryService;
    private final PetOwnerCommandService petOwnerCommandService;
    private final PetOwnerQueryService petOwnerQueryService;

    public ProfilesContextFacade(VetCenterCommandService vetCenterCommandService, VetCenterQueryService vetCenterQueryService,
                                 PetOwnerCommandService petOwnerCommandService,PetOwnerQueryService petOwnerQueryService
    ) {
        this.vetCenterCommandService = vetCenterCommandService;
        this.vetCenterQueryService = vetCenterQueryService;
        this.petOwnerCommandService = petOwnerCommandService;
        this.petOwnerQueryService = petOwnerQueryService;
    }

    public Long fetchPetOwnerIdById(Long petOwnerId){
        var getPetOwnerByIdQuery = new GetPetOwnerByIdQuery(petOwnerId);
        var petOwner = petOwnerQueryService.handle(getPetOwnerByIdQuery);
        if (petOwner.isEmpty()) return 0L;
        return petOwner.get().getId();
    }

    public Long fetchVetCenterIdById(Long vetCenterId){
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var vetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (vetCenter.isEmpty()) return 0L;
        return vetCenter.get().getId();
    }
}
