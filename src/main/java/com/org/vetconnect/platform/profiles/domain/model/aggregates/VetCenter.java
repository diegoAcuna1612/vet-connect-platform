package com.org.vetconnect.platform.profiles.domain.model.aggregates;

import com.org.vetconnect.platform.profiles.domain.model.entities.VetCenterImage;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterEmail;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import com.org.vetconnect.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class VetCenter extends AuditableAbstractAggregateRoot<VetCenter> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Setter
    private VetCenterName vetCenterName;

    @Embedded
    @Setter
    private VetCenterEmail vetCenterEmail;

    @Embedded
    @Setter
    private VetCenterRUC vetCenterRUC;

    @Embedded
    @Setter
    private VetCenterPhone vetCenterPhone;

    @OneToMany(mappedBy = "vetCenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VetCenterImage> images = new ArrayList<>();
    /*
    @Setter
    private String vetCenterServices; // seria un record (porque tiene 4 servicios) y cada servicio tiene un nombre y un precio
    */

    @Getter
    @Setter
    private String vetCenterImageProfile;

    @Getter
    @Setter
    private String vetCenterDescription;

    public VetCenter(String name, String email, Long ruc, Long phone, String imageProfile, String description){
        this.vetCenterName = new VetCenterName(name);
        this.vetCenterEmail = new VetCenterEmail(email);
        this.vetCenterRUC = new VetCenterRUC(ruc);
        this.vetCenterPhone = new VetCenterPhone(phone);
        this.vetCenterImageProfile = imageProfile;
        this.vetCenterDescription = description;

    }

    public VetCenter(){

    }

    public void addImage(String url) {
        if (this.images.size() >= 6) {
            throw new IllegalArgumentException("Cannot add more than 6 images");
        }
        VetCenterImage image = new VetCenterImage(url);
        image.setVetCenter(this); // establecer la relación bidireccional
        this.images.add(image);
    }

    public void removeImage(Long imageId) {
        this.images.removeIf(image -> image.getId().equals(imageId));
    }

    public List<VetCenterImage> getImages() {
        return images;
    }

    public void setImages(List<VetCenterImage> images) {
        if (images.size() > 6) {
            throw new IllegalArgumentException("Cannot have more than 6 images");
        }
        this.images = images;
    }

    public String getName(){
        return this.vetCenterName.getFullName();
    }

    public String getEmail(){
        return this.vetCenterEmail.email();
    }

    public Long getRUC(){
        return this.vetCenterRUC.vetCenterRUC();
    }

    public Long getPhone(){
        return this.vetCenterPhone.getVetCenterPhone();
    }

    public void setName(String name){
        this.vetCenterName = new VetCenterName(name);
    }

    public void setEmail(String email){
        this.vetCenterEmail = new VetCenterEmail(email);
    }

    public void setPhone(Long phone){
        this.vetCenterPhone = new VetCenterPhone(phone);
    }

    public void setRUC(Long ruc){
        this.vetCenterRUC = new VetCenterRUC(ruc);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
