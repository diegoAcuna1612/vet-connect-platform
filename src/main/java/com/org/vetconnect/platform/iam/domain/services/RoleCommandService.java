package com.org.vetconnect.platform.iam.domain.services;


import com.org.vetconnect.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
