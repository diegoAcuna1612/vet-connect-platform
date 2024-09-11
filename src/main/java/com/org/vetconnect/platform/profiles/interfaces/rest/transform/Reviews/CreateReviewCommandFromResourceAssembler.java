package com.org.vetconnect.platform.profiles.interfaces.rest.transform.Reviews;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateReviewCommand;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews.CreateReviewResource;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewCommandFromResourceAssembler {

    public CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.petOwnerId(),
                resource.vetCenterId(),
                resource.comments(),
                resource.rating()
        );
    }
}
