package com.org.vetconnect.platform.profiles.domain.model.entities;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class VetCenterImage extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String url;

    @ManyToOne
    @JoinColumn(name = "vet_center_id", nullable = false)
    private VetCenter vetCenter;

    public VetCenterImage() {}

    public VetCenterImage(String url) {
        this.url = url;
    }

    public void setVetCenter(VetCenter vetCenter) {
        this.vetCenter = vetCenter;
    }

}
