package com.gabriel.pive.fiv.services;

import com.gabriel.pive.animals.entities.Bull;
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

    public List<FivResponseDto> getAllFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findAll());
    }

    public FivResponseDto getFivById(Long id){
        Fiv fiv = fivRepository.findById(id).
            orElseThrow(FivNotFoundException::new);

        return FivResponseDto.toFivResponseDto(fiv);
    }

    public List<FivResponseDto> getInProcessFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatus(FivStatusEnum.IN_PROCESS));
    }

    public List<FivResponseDto> getOocyteCollectionCompletedFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatus(FivStatusEnum.OOCYTE_COLLECTION_COMPLETED));
    }

    public List<FivResponseDto> getCompletedFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatus(FivStatusEnum.COMPLETED));
    }

    public List<FivResponseDto> filterFivesByBull(Long bullId){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByOocyteCollection_Bull_Id(bullId));
    }

    public List<FivResponseDto> filterFivesByDonor(Long donorId){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByOocyteCollection_DonorCattle_Id(donorId));
    }



}
