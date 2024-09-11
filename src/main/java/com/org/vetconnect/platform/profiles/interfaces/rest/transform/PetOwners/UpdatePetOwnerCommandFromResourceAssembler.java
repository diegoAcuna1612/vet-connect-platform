package com.org.vetconnect.platform.profiles.interfaces.rest.transform.PetOwners;

import com.org.vetconnect.platform.profiles.domain.model.commands.UpdatePetOwnerCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners.CreatePetOwnerResource;

public class UpdatePetOwnerCommandFromResourceAssembler {

    public static UpdatePetOwnerCommand toCommandFromResource(CreatePetOwnerResource resource, Long petOwnerId) {
        UpdatePetOwnerCommand command = new UpdatePetOwnerCommand(
                petOwnerId,
                resource.name(),
                resource.email(),
                resource.dni(),
                resource.phone(),
                resource.photo()
        );
        return command;
    }
}
