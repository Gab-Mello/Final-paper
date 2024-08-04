package com.gabriel.pive.services;

import com.gabriel.pive.dtos.DonorCattleDto;
import com.gabriel.pive.dtos.ReceiverCattleDto;
import com.gabriel.pive.entities.DonorCattle;
import com.gabriel.pive.entities.ReceiverCattle;
import com.gabriel.pive.repositories.DonorCattleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorCattleService {

    @Autowired
    DonorCattleRepository donorCattleRepository;

    public DonorCattleDto create(DonorCattleDto dto){
        DonorCattle donorCattle = donorCattleRepository.save(dto.toDonorCattle());
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public List<DonorCattleDto> findAll(){
        List<DonorCattle> list = donorCattleRepository.findAll();
        return DonorCattleDto.toDonorCattleDtoList(list);
    }

    public DonorCattleDto findById(Long id){
        Optional<DonorCattle> donorCattle = donorCattleRepository.findById(id);
        if (donorCattle.isEmpty()){
            return null;
        }
        return DonorCattleDto.toDonorCattleDto(donorCattle.get());
    }

    public void delete(Long id){
        donorCattleRepository.deleteById(id);
    }

    public DonorCattleDto edit(Long id, DonorCattleDto dto){
        Optional<DonorCattle> optionalDonorCattle= donorCattleRepository.findById(id);
        DonorCattle donorCattle = optionalDonorCattle.get();

        donorCattle.setName(dto.name());
        donorCattle.setBreed(dto.breed());
        donorCattle.setBirth(dto.birth());
        donorCattle.setRegistrationNumber(dto.registrationNumber());

        return DonorCattleDto.toDonorCattleDto(donorCattleRepository.save(donorCattle));
    }
}
