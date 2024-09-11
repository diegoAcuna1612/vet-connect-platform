package com.org.vetconnect.platform.iam.domain.model.queries;


import com.org.vetconnect.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
