package com.org.vetconnect.platform.appointments.application.outboundServices;

import com.org.vetconnect.platform.appointments.domain.model.valueobjects.PetOwnerId;
import com.org.vetconnect.platform.appointments.domain.model.valueobjects.VetCenterId;
import com.org.vetconnect.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfilesService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfilesService(ProfilesContextFacade profilesContextFacade){
        this.profilesContextFacade =profilesContextFacade;
    }

    public Optional<VetCenterId> fetchVetCenterIdById(Long vetCenterId){
        var vetId = profilesContextFacade.fetchVetCenterIdById(vetCenterId);
        if (vetId == 0L) return Optional.empty();
        return Optional.of(new VetCenterId(vetId));
    }

    public Optional<PetOwnerId> fetchPetOwnerIdById(Long petOwnerId){
        var ownerId = profilesContextFacade.fetchPetOwnerIdById(petOwnerId);
        if (ownerId == 0L) return Optional.empty();
        return Optional.of(new PetOwnerId(ownerId));
    }
}
