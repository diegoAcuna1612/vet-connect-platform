package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterResource;

public class CreateVetCenterCommandFromResourceAssembler {

    public static CreateVetCenterCommand toCommandFromResource(CreateVetCenterResource resource) {
        return new CreateVetCenterCommand(
                resource.name(),
                resource.email(),
                resource.ruc(),
                resource.phone(),
                resource.imageProfile(),
                resource.description()
        );
    }
}
