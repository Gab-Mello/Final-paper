package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.exceptions.BullNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
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

        if (bullRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Um touro com este número de registro já existe.");
        }
        Bull bull = bullRepository.save(dto.toBull());
        return BullDto.toBullDto(bull);
    }

    public List<BullDto> findAll(){
        List<Bull> list = bullRepository.findAll();
        return BullDto.toBullDtoList(list);
    }

    public BullDto findById(Long id){
        Bull bull = bullRepository.findById(id)
                .orElseThrow(() -> new BullNotFoundException());
        return BullDto.toBullDto(bull);
    }

    public BullDto findByRegistrationNumber(String registrationNumber){
        return BullDto.toBullDto(bullRepository.
                findByRegistrationNumber(registrationNumber));
    }

    public void delete(Long id){
        bullRepository.deleteById(id);
    }

    public BullDto edit(Long id, BullDto dto){
        Bull bull = bullRepository.findById(id)
                .orElseThrow(() -> new BullNotFoundException());

        if (bullRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Um touro com este número de registro já existe.");
        }

        bull.setName(dto.name());
        bull.setRegistrationNumber(dto.registrationNumber());

        return BullDto.toBullDto(bullRepository.save(bull));
    }
}
