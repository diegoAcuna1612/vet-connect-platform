package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.commands.UpdateVetCenterCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterResource;

public class UpdateVetCenterCommandFromResourceAssembler {

        public static UpdateVetCenterCommand toCommandFromResource(CreateVetCenterResource resource, Long vetCenterId) {
            UpdateVetCenterCommand command = new UpdateVetCenterCommand(
                    vetCenterId,
                    resource.name(),
                    resource.email(),
                    resource.ruc(),
                    resource.phone(),
                    resource.imageProfile(),
                    resource.description()
            );
            return command;
        }
}
