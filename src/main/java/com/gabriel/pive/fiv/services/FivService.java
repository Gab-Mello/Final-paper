package com.gabriel.pive.fiv.services;

import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FivService {

    @Autowired
    private FivRepository fivRepository;

    public FivResponseDto createFiv(){
        Fiv fiv = new Fiv();
        fiv.setStatus(FivStatusEnum.IN_PROCESS);
        return FivResponseDto.toFivResponseDto(fivRepository.save(fiv));
    }

    public List<FivResponseDto> getAllFivs(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findAll());
    }

    public FivResponseDto getFivById(Long id){
        Fiv fiv = fivRepository.findById(id).
            orElseThrow(() -> new FivNotFoundException("Fiv not found."));

        return FivResponseDto.toFivResponseDto(fiv);
    }

}
