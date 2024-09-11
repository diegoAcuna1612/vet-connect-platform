package com.org.vetconnect.platform.profiles.interfaces.rest.transform.PetOwners;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners.PetOwnerResource;

public class PetOwnerResourceFromEntityAssembler {

    public static PetOwnerResource toResourceFromEntity(PetOwner petOwner){
        return new PetOwnerResource(
                petOwner.getId(),
                petOwner.getName(),
                petOwner.getEmail(),
                petOwner.getDNI(),
                petOwner.getPhone(),
                petOwner.getPhoto()
        );
    }
}
