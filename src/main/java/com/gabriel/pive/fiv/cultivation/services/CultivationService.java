package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.FivAlreadyHasCultivation;
import com.gabriel.pive.fiv.cultivation.exceptions.FivDoesNotHaveOocyteCollectionException;
import com.gabriel.pive.fiv.cultivation.exceptions.MoreViableThanTotalEmbryosException;
import com.gabriel.pive.fiv.cultivation.repositories.CultivationRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CultivationService {

    @Autowired
    private CultivationRepository cultivationRepository;

    @Autowired
    private FivRepository fivRepository;

    public CultivationResponseDto newCultivation(CultivationRequestDto dto){

        Fiv fiv = fivRepository.findById(dto.fivId())
                .orElseThrow(FivNotFoundException::new);

        if (fiv.getOocyteCollections().isEmpty()){
            throw new FivDoesNotHaveOocyteCollectionException();
        }

        if (fiv.getCultivation() != null){
            throw  new FivAlreadyHasCultivation();
        }

        if (dto.viableEmbryos() > dto.totalEmbryos()){
            throw new MoreViableThanTotalEmbryosException();
        }

        Cultivation cultivation = dto.toCultivation(fiv);

        Cultivation savedCultivation = cultivationRepository.save(cultivation);

        fiv.setCultivation(savedCultivation);
        fivRepository.save(fiv);

        return CultivationResponseDto.toCultivationResponseDto(savedCultivation);

    }

    public List<CultivationResponseDto> getAllCultivations(){
        return CultivationResponseDto.toCultivationDtoList(cultivationRepository.findAll());
    }

    public CultivationResponseDto getCultivationById(Long id){
        Cultivation cultivation = cultivationRepository.findById(id)
                .orElseThrow(CultivationNotFoundException::new);

        return CultivationResponseDto.toCultivationResponseDto(cultivation);
    }

    public CultivationResponseDto editCultivation(Long id, CultivationRequestDto dto){
        Cultivation cultivation = cultivationRepository.findById(id)
                .orElseThrow(CultivationNotFoundException::new);

        Fiv newFiv = fivRepository.findById(dto.fivId())
                .orElseThrow(FivNotFoundException::new);

        if (newFiv.getCultivation() != null){
            throw  new FivAlreadyHasCultivation();
        }

        if (newFiv.getOocyteCollections().isEmpty()){
            throw new FivDoesNotHaveOocyteCollectionException();
        }

        Fiv oldFiv = fivRepository.findByCultivationId(id);
        oldFiv.setCultivation(null);
        fivRepository.save(oldFiv);


        cultivation.setFiv(newFiv);
        cultivation.setTotalEmbryos(dto.totalEmbryos());
        cultivation.setViableEmbryos(dto.viableEmbryos());

        Cultivation cultivationSaved = cultivationRepository.save(cultivation);

        newFiv.setCultivation(cultivationSaved);
        fivRepository.save(newFiv);

        return CultivationResponseDto.toCultivationResponseDto(cultivationSaved);

    }
}
