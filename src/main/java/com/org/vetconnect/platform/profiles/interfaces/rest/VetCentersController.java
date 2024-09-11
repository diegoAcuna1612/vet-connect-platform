package com.org.vetconnect.platform.profiles.interfaces.rest;

import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllVetCenterImagesByVetCenterIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllVetCentersQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetVetCenterByNameQuery;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterName;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterCommandService;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterQueryService;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterImageResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.CreateVetCenterResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.VetCenterImageResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.VetCenters.VetCenterResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.VetCenters.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/vet-centers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vet Centers", description = "Vet Centers Management Endpoints")
public class VetCentersController {

    private final VetCenterQueryService vetCenterQueryService;
    private final VetCenterCommandService vetCenterCommandService;

    public VetCentersController(VetCenterQueryService vetCenterQueryService, VetCenterCommandService vetCenterCommandService) {
        this.vetCenterQueryService = vetCenterQueryService;
        this.vetCenterCommandService = vetCenterCommandService;
    }

    // crear centro veterinario
    @PostMapping
    public ResponseEntity<VetCenterResource> createVetCenter(@RequestBody CreateVetCenterResource resource) {
        var createVetCenterCommand = CreateVetCenterCommandFromResourceAssembler.toCommandFromResource(resource);
        var vetCenterId = vetCenterCommandService.handle(createVetCenterCommand);
        if (vetCenterId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var vetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (vetCenter.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return new ResponseEntity<>(vetCenterResource, HttpStatus.CREATED);
    }

    // obtener centro veterinario por id
    @GetMapping("/{vetCenterId}")
    public ResponseEntity<VetCenterResource> getVetCenterById(@PathVariable Long vetCenterId) {
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var vetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (vetCenter.isEmpty()) return ResponseEntity.badRequest().build();
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return ResponseEntity.ok(vetCenterResource);
    }

    // obtener centro veterinario por nombre
    @GetMapping("/name/{vetCenterName}")
    public ResponseEntity<VetCenterResource> getVetCenterByName(@PathVariable String vetCenterName) {
        var getVetCenterByNameQuery = new GetVetCenterByNameQuery(new VetCenterName(vetCenterName));
        var vetCenter = vetCenterQueryService.handle(getVetCenterByNameQuery);
        if (vetCenter.isEmpty()) return ResponseEntity.badRequest().build();
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(vetCenter.get());
        return ResponseEntity.ok(vetCenterResource);
    }

    // actualizar centro veterinario
    @PutMapping("/{vetCenterId}")
    public ResponseEntity<VetCenterResource> updateVetCenter(
            @PathVariable Long vetCenterId,
            @RequestBody CreateVetCenterResource resource) {

        // 1. Obtener el centro veterinario existente
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var existingVetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);

        // 2. Comprobar si se encuentra
        if (existingVetCenter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 3. Crear UpdateVetCenterCommand
        var updateVetCenterCommand = UpdateVetCenterCommandFromResourceAssembler.toCommandFromResource(resource, vetCenterId);

        // 4. Actualizar el centro veterinario existente
        vetCenterCommandService.handle(updateVetCenterCommand);

        // 5. Recuperar el centro veterinario actualizado (opcional)
        var updatedVetCenter = existingVetCenter.get(); // Usar el objeto existente si no se recupera en el servicio
        var vetCenterResource = VetCenterResourceFromEntityAssembler.toResourceFromEntity(updatedVetCenter);

        return new ResponseEntity<>(vetCenterResource, HttpStatus.OK);
    }

    // obtener todos los centros veterinarios
    @GetMapping
    public ResponseEntity<List<VetCenterResource>> getAllVetCenters() {
        var getAllVetCentersQuery = new GetAllVetCentersQuery();
        var vetCenters = vetCenterQueryService.handle(getAllVetCentersQuery);
        var vetCenterResources = vetCenters.stream().map(VetCenterResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(vetCenterResources);
    }

    @PostMapping("/{vetCenterId}/images")
    public ResponseEntity<String> updateImage(@PathVariable Long vetCenterId, @RequestBody CreateVetCenterImageResource resource){
        var getVetCenterByIdQuery = new GetVetCenterByIdQuery(vetCenterId);
        var existingVetCenter = vetCenterQueryService.handle(getVetCenterByIdQuery);
        if (existingVetCenter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var createVetCenterCommand = CreateVetCenterImageCommandFromResource.ToCommandFromResource(vetCenterId,resource);
        var result = vetCenterCommandService.handle(createVetCenterCommand);
        return ResponseEntity.ok("");
    }

    @GetMapping("/{vetCenterId}/images")
    public ResponseEntity<List<VetCenterImageResource>> getAllVetCenterImages(@PathVariable Long vetCenterId){
        var getAllVetCenterImagesQuery = new GetAllVetCenterImagesByVetCenterIdQuery(vetCenterId);
        var vetCenterImages = vetCenterQueryService.handle(getAllVetCenterImagesQuery);
        var vetCenterResources = vetCenterImages.stream()
                .map(VetCenterImageResourceFromEntityAssembler::ToResourceFromEntity)
                .toList();
        return ResponseEntity.ok(vetCenterResources);
    }
}
