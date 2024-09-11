package com.org.vetconnect.platform.profiles.domain.model.queries;

import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;

public record GetVetCenterByNameQuery(VetCenterName vetCenterName) {
}