package com.org.vetconnect.platform.iam.interfaces.rest.transform;

import com.org.vetconnect.platform.iam.domain.model.aggregates.User;
import com.org.vetconnect.platform.iam.domain.model.entities.Role;
import com.org.vetconnect.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {

        String role = user.getRoles().stream()
                .map(Role::getStringName)
                .findFirst()
                .orElse("ROLE_USER");

        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token, role);
    }
}
