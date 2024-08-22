package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.exceptions.CultivationNotFoundException;
import com.gabriel.pive.fiv.cultivation.exceptions.FivAlreadyHasCultivation;
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
                .orElseThrow(() -> new FivNotFoundException("Fiv not found"));

        if (fiv.getCultivation() != null){
            throw  new FivAlreadyHasCultivation("This fiv already has a cultivation registered");
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
                .orElseThrow(() -> new CultivationNotFoundException("Cultivation not found"));

        return CultivationResponseDto.toCultivationResponseDto(cultivation);
    }

    public CultivationResponseDto editCultivation(Long id, CultivationRequestDto dto){
        Cultivation cultivation = cultivationRepository.findById(id)
                .orElseThrow(() -> new CultivationNotFoundException("Cultivation not found"));

        Fiv newFiv = fivRepository.findById(dto.fivId())
                .orElseThrow(() -> new FivNotFoundException("Fiv not found"));

        Fiv oldFiv = fivRepository.findByCultivationId(id);
        oldFiv.setCultivation(null);
        fivRepository.save(oldFiv);

        if (newFiv.getCultivation() != null){
            throw  new FivAlreadyHasCultivation("This fiv already has a cultivation registered");
        }

        cultivation.setFiv(newFiv);
        cultivation.setTotalEmbryos(dto.totalEmbryos());
        cultivation.setViableEmbryos(dto.viableEmbryos());

        Cultivation cultivationSaved = cultivationRepository.save(cultivation);

        newFiv.setCultivation(cultivationSaved);
        fivRepository.save(newFiv);

        return CultivationResponseDto.toCultivationResponseDto(cultivationSaved);

    }
}
