package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorCattleService {

    @Autowired
    DonorCattleRepository donorCattleRepository;

    public DonorCattleDto create(DonorCattleDto dto){
        if (donorCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma doadora com este número de registro já existe.");
        }
        DonorCattle donorCattle = donorCattleRepository.save(dto.toDonorCattle());
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public List<DonorCattleDto> findAll(){
        List<DonorCattle> list = donorCattleRepository.findAll();
        return DonorCattleDto.toDonorCattleDtoList(list);
    }

    public DonorCattleDto findById(Long id){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                .orElseThrow(() -> new DonorCattleNotFoundException());
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public DonorCattleDto findByRegistrationNumber(String registrationNumber){
        return DonorCattleDto.toDonorCattleDto(donorCattleRepository.
                findByRegistrationNumber(registrationNumber));
    }

    public void delete(Long id){
        donorCattleRepository.deleteById(id);
    }

    public DonorCattleDto edit(Long id, DonorCattleDto dto){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                .orElseThrow(() -> new DonorCattleNotFoundException());

        if (donorCattleRepository.findByRegistrationNumber(dto.registrationNumber()) != null){
            throw new RegistrationNumberAlreadyExistsException("Uma doadora com este número de registro já existe.");
        }

        donorCattle.setName(dto.name());
        donorCattle.setBreed(dto.breed());
        donorCattle.setBirth(dto.birth());
        donorCattle.setRegistrationNumber(dto.registrationNumber());

        return DonorCattleDto.toDonorCattleDto(donorCattleRepository.save(donorCattle));
    }
}
