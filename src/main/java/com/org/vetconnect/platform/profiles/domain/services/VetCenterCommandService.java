package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterImageCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.UpdateVetCenterCommand;

public interface VetCenterCommandService {
    Long handle(CreateVetCenterCommand command);
    Long handle(UpdateVetCenterCommand command);
    Long handle(CreateVetCenterImageCommand command);
}
