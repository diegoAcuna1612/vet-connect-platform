package com.org.vetconnect.platform.profiles.interfaces.rest;

import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllPetOwnersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetPetOwnerByIdQuery;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerCommandService;
import com.org.vetconnect.platform.profiles.domain.services.PetOwnerQueryService;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners.CreatePetOwnerResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.PetOwners.PetOwnerResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.PetOwners.CreatePetOwnerCommandFromResourceAssembler;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.PetOwners.PetOwnerResourceFromEntityAssembler;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.PetOwners.UpdatePetOwnerCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/pet-owners", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Pet Owners", description = "Pet Owners Management Endpoints")
public class PetOwnersController {

    private final PetOwnerQueryService petOwnerQueryService;
    private final PetOwnerCommandService petOwnerCommandService;


    public PetOwnersController(PetOwnerQueryService petOwnerQueryService, PetOwnerCommandService petOwnerCommandService) {
        this.petOwnerQueryService = petOwnerQueryService;
        this.petOwnerCommandService = petOwnerCommandService;
    }

    // crear propietario de mascota
    @PostMapping
    public ResponseEntity<PetOwnerResource> createPetOwner(@RequestBody CreatePetOwnerResource resource) {
        var createPetOwnerCommand = CreatePetOwnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var petOwnerId = petOwnerCommandService.handle(createPetOwnerCommand);
        if (petOwnerId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getPetOwnerByIdQuery = new GetPetOwnerByIdQuery(petOwnerId);
        var petOwner = petOwnerQueryService.handle(getPetOwnerByIdQuery);
        if (petOwner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var petOwnerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(petOwner.get());
        return new ResponseEntity<>(petOwnerResource, HttpStatus.CREATED);
    }

    // obtener propietario de mascota por id
    @GetMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> getPetOwnerById(@PathVariable Long petOwnerId) {
        var getPetOwnerByIdQuery = new GetPetOwnerByIdQuery(petOwnerId);
        var petOwner = petOwnerQueryService.handle(getPetOwnerByIdQuery);
        if (petOwner.isEmpty()) return ResponseEntity.badRequest().build();
        var petOwnerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(petOwner.get());
        return new ResponseEntity<>(petOwnerResource, HttpStatus.OK);
    }

    // actualizar propietario de mascota por id
    @PutMapping("/{petOwnerId}")
    public ResponseEntity<PetOwnerResource> updatePetOwner(
            @PathVariable Long petOwnerId,
            @RequestBody CreatePetOwnerResource resource) {

        // 1. obtener el petOwner existente
        var getPetOwnerByIdQuery = new GetPetOwnerByIdQuery(petOwnerId);
        var existingPetOwner = petOwnerQueryService.handle(getPetOwnerByIdQuery);

        // 2. verificar si el petOwner existe
        if (existingPetOwner.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 3. comando para actualizar el petOwner
        var updatePetOwnerCommand = UpdatePetOwnerCommandFromResourceAssembler.toCommandFromResource(resource, petOwnerId);

        // 4. actualizar el petOwner existente
        var updatedPetOwnerId = petOwnerCommandService.handle(updatePetOwnerCommand);

        // 5. recuperar el petOwner actualizado (opcional)
        var updatePetOwner = existingPetOwner.get();
        var petOwnerResource = PetOwnerResourceFromEntityAssembler.toResourceFromEntity(updatePetOwner);

        return new ResponseEntity<>(petOwnerResource, HttpStatus.OK);

    }

    // obtener todos los propietarios de mascotas
    @GetMapping
    public ResponseEntity<List<PetOwnerResource>> getAllPetOwners() {
        var getAllPetOwnersQuery = new GetAllPetOwnersQuery();
        var petOwners = petOwnerQueryService.handle(getAllPetOwnersQuery);
        var petOwnerResources = petOwners.stream()
                .map(PetOwnerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(petOwnerResources);
    }
}


