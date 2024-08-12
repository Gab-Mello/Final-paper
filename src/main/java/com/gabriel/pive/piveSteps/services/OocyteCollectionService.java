package com.gabriel.pive.piveSteps.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.piveSteps.dtos.OocyteCollectionDto;
import com.gabriel.pive.piveSteps.entities.OocyteCollection;
import com.gabriel.pive.piveSteps.repositories.OocyteCollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new EntityNotFoundException("Bull not found")));

        OocyteCollection oocyteCollection = dto.toOocyteCollection(donorCattle, bull);

        collectionRepository.save(oocyteCollection);

    }
}
