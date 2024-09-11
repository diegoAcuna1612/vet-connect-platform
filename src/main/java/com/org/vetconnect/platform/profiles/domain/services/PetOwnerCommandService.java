package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreatePetOwnerCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.UpdatePetOwnerCommand;

public interface PetOwnerCommandService {
    Long handle(CreatePetOwnerCommand command);
    Long handle(UpdatePetOwnerCommand command);
}
