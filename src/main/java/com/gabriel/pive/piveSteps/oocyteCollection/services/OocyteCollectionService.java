package com.gabriel.pive.piveSteps.oocyteCollection.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.animals.services.BullService;
import com.gabriel.pive.animals.services.DonorCattleService;
import com.gabriel.pive.piveSteps.oocyteCollection.dtos.OocyteCollectionDto;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.piveSteps.oocyteCollection.repositories.OocyteCollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OocyteCollectionService {

    @Autowired
    private OocyteCollectionRepository collectionRepository;

    @Autowired
    private DonorCattleRepository donorCattleRepository;

    @Autowired
    private BullRepository bullRepository;

    public OocyteCollectionDto newOocyteCollection(OocyteCollectionDto dto){
        DonorCattle donorCattle = donorCattleRepository.findById(dto.donorCattleId())
                .orElseThrow(() -> new EntityNotFoundException("Donor not found"));
        System.out.println(donorCattle.getName());

        Bull bull = bullRepository.findById(dto.bullId())
                .orElseThrow(() -> new EntityNotFoundException("Bull not found"));

        OocyteCollection oocyteCollection = dto.toOocyteCollection(donorCattle, bull);

        return OocyteCollectionDto.toOocyteCollectionDto(collectionRepository.save(oocyteCollection));

    }

}
