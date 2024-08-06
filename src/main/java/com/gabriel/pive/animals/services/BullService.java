package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.repositories.BullRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BullService {

    @Autowired
    private BullRepository bullRepository;

    public BullDto create(BullDto dto){
        Bull bull = bullRepository.save(dto.toBull());
        return BullDto.toBullDto(bull);
    }

    public List<BullDto> findAll(){
        List<Bull> list = bullRepository.findAll();
        return BullDto.toBullDtoList(list);
    }

    public BullDto findById(Long id){
        Optional<Bull> bull = bullRepository.findById(id);
        if (bull.isEmpty()){
            return null;
        }
        return BullDto.toBullDto(bull.get());
    }

    public List<BullDto> findByRegistrationNumber(String registrationNumber){
        return BullDto.toBullDtoList(bullRepository.
                findByRegistrationNumber(registrationNumber));
    }

    public void delete(Long id){
        bullRepository.deleteById(id);
    }

    public BullDto edit(Long id, BullDto dto){
        Optional<Bull> optionalBull= bullRepository.findById(id);
        Bull bull = optionalBull.get();

        bull.setName(dto.name());
        bull.setRegistrationNumber(dto.registrationNumber());

        return BullDto.toBullDto(bullRepository.save(bull));
    }
}
