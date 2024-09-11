package com.org.vetconnect.platform.profiles.domain.model.entities;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.PetOwner;
import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
public class Review extends AuditableModel {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // un vet center puede tener varias reviews
    @ManyToOne
    @Getter
    @JoinColumn(name="pet_owner_id", nullable = false)
    private PetOwner petOwner;

    // una review solo puede pertenecer a un pet owner
    @ManyToOne
    @Getter
    @JoinColumn(name="vet_center_id", nullable = false)
    private VetCenter vetCenter;

    @Getter
    private String comments;

    @Getter
    private int rating;


    public Review(){
    }

    public Review(PetOwner petOwner, VetCenter vetCenter, String comments, int rating) {
        this.petOwner = petOwner;
        this.vetCenter = vetCenter;
        this.comments = comments;
        this.rating = rating;
    }
}
