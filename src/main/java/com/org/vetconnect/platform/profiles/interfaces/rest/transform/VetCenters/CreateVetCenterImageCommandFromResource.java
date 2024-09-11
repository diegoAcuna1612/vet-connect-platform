package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterImageCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterImageResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterResource;
import org.springframework.stereotype.Component;

@Component
public class CreateVetCenterImageCommandFromResource {
    public static CreateVetCenterImageCommand ToCommandFromResource(Long vetCenterId,CreateVetCenterImageResource resource){
        return new CreateVetCenterImageCommand( vetCenterId,resource.imageUrl());
    }
}
