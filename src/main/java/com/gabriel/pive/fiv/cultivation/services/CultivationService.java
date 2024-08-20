package com.gabriel.pive.fiv.cultivation.services;

import com.gabriel.pive.fiv.cultivation.dtos.CultivationRequestDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.cultivation.repositories.CultivationRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CultivationService {

    @Autowired
    private CultivationRepository cultivationRepository;

    @Autowired
    private FivRepository fivRepository;

    public CultivationResponseDto newCultivation(CultivationRequestDto dto){

        Fiv fiv = fivRepository.findById(dto.fivId())
                .orElseThrow(() -> new FivNotFoundException("Fiv not found"));

        Cultivation cultivation = dto.toCultivation(fiv);

        Cultivation savedCultivation = cultivationRepository.save(cultivation);

        fiv.setCultivation(savedCultivation);
        fivRepository.save(fiv);


        return CultivationResponseDto.toCultivationResponseDto(savedCultivation);

    }
}
