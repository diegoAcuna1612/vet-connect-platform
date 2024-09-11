package com.org.vetconnect.platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token, String role) {

}
