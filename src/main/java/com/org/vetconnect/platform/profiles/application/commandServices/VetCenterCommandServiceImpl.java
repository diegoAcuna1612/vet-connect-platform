package com.org.vetconnect.platform.profiles.application.commandServices;

import com.org.vetconnect.platform.profiles.domain.model.aggregates.VetCenter;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.CreateVetCenterImageCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.UpdateVetCenterCommand;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterPhone;
import com.org.vetconnect.platform.profiles.domain.model.valueobjects.VetCenterRUC;
import com.org.vetconnect.platform.profiles.domain.services.VetCenterCommandService;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterImageRepository;
import com.org.vetconnect.platform.profiles.infrastructure.persistence.jpa.repositories.VetCenterRepository;
import org.springframework.stereotype.Service;

@Service
public class VetCenterCommandServiceImpl implements VetCenterCommandService {

    private final VetCenterRepository vetCenterRepository;
    private  final VetCenterImageRepository vetCenterImageRepository;
    public VetCenterCommandServiceImpl(VetCenterRepository vetCenterRepository,VetCenterImageRepository vetCenterImageRepository) {
        this.vetCenterRepository = vetCenterRepository;
        this.vetCenterImageRepository= vetCenterImageRepository;
    }


    @Override
    public Long handle(CreateVetCenterCommand command) {

        // Validate that the vet center has a unique RUC
        var ruc =  new VetCenterRUC(command.ruc());

        vetCenterRepository.findVetCenterByVetCenterRUC(ruc)
                .map(vetCenter -> {
                    throw new IllegalArgumentException("VetCenter already exists with RUC "
                            + ruc.vetCenterRUC() + "");
                });

        // Create a new vet center and save it
        var vetCenter = new VetCenter(
                command.name(),
                command.email(),
                command.ruc(),
                command.phone(),
                command.imageProfile(),
                command.description()
        );

        vetCenterRepository.save(vetCenter);
        return vetCenter.getId();
    }

    @Override
    public Long handle(UpdateVetCenterCommand command) {
        var vetCenter = vetCenterRepository.findById(command.id()).orElseThrow(() -> new IllegalArgumentException("VetCenter with id " + command.id() + " does not exist"));

        // si el nombre es diferente de null, de "string" y no esta vacio, actualiza el nombre
        if (command.name() != null && !command.name().equals("string") && !command.name().isEmpty()) {
            vetCenter.setName(command.name());
        }

        // si el email es diferente de null, de "string" y no esta vacio, actualiza el email
        if (command.email() != null && !command.email().equals("string") && !command.email().isEmpty()) {
            vetCenter.setEmail(command.email());
        }

        // si el phone es diferente de null, de 0 y no esta vacio, actualiza el phone
        if (command.phone() != null && command.phone() != 0) {
            vetCenter.setVetCenterPhone(new VetCenterPhone(command.phone()));
        }

        /**
         * para guardar sin validaciones simplemente se pondria:
         * vetCenter.setName(command.name());
         * vetCenter.setEmail(command.email());
         * vetCenter.setVetCenterPhone(new VetCenterPhone(command.phone()));
         * */

        vetCenterRepository.save(vetCenter);
        return vetCenter.getId();
    }

    @Override
    public Long handle(CreateVetCenterImageCommand command) {
        var vetCenterOpt = vetCenterRepository.findById(command.vetCenterId());
        if (vetCenterOpt.isPresent()){
            var vetCenter = vetCenterOpt.get();
            vetCenter.addImage(command.imageUrl());
            vetCenterRepository.save(vetCenter);
            return vetCenter.getId();
        }
        else {
            throw new IllegalArgumentException("VetCenter not found");
        }
    }
}
