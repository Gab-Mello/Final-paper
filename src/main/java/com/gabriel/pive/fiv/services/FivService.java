package com.gabriel.pive.fiv.services;

import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FivService {

    @Autowired
    private FivRepository fivRepository;

    public FivResponseDto createFiv(){
        return FivResponseDto.toFivResponseDto(fivRepository.save(new Fiv()));
    }

    public List<FivResponseDto> getAllFivs(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findAll());
    }

}
