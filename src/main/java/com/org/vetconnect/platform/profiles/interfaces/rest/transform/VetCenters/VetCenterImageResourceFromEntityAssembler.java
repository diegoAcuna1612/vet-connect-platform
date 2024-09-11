package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.entities.VetCenterImage;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.VetCenterImageResource;

public class VetCenterImageResourceFromEntityAssembler {
    public static VetCenterImageResource ToResourceFromEntity(VetCenterImage vetCenterImage){
        return new VetCenterImageResource(vetCenterImage.getId(), vetCenterImage.getUrl());
    }
}
