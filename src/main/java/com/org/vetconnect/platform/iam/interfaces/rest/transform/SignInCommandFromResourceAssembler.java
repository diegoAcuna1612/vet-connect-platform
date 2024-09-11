package com.org.vetconnect.platform.iam.interfaces.rest.transform;

import com.org.vetconnect.platform.iam.domain.model.commands.SignInCommand;
import com.org.vetconnect.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
