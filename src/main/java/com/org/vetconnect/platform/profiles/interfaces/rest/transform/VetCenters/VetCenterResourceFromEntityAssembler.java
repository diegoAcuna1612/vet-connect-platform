package com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.VetCenterResource;

public class VetCenterResourceFromEntityAssembler {

    public static VetCenterResource toResourceFromEntity(VetCenter vetCenter){
        return new VetCenterResource(
                vetCenter.getId(),
                vetCenter.getName(),
                vetCenter.getEmail(),
                vetCenter.getRUC(),
                vetCenter.getPhone(),
                vetCenter.getVetCenterImageProfile(),
                vetCenter.getVetCenterDescription()
        );
    }

}
