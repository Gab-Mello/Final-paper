package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.dtos.ReceiverCattleDto;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.cultivation.entities.Embryo;
import com.gabriel.pive.fiv.cultivation.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorCattleService {

    @Autowired
    private DonorCattleRepository donorCattleRepository;

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private OocyteCollectionRepository oocyteCollectionRepository;

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
                .orElseThrow(DonorCattleNotFoundException::new);
        return DonorCattleDto.toDonorCattleDto(donorCattle);
    }

    public List<DonorCattleDto> findByRegistrationNumber(String registrationNumber){
        return DonorCattleDto.toDonorCattleDtoList(donorCattleRepository.
                findByRegistrationNumberStartingWith(registrationNumber));
    }

    public void delete(Long id){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                        .orElseThrow(DonorCattleNotFoundException::new);

        List<Embryo> embryos = embryoRepository.findAllByEmbryoDonorCattle(donorCattle);
        for (Embryo embryo : embryos){
            embryo.setEmbryoDonorCattle(null);
            embryoRepository.save(embryo);
        }

        List<OocyteCollection> oocyteCollections = oocyteCollectionRepository.findAllByDonorCattle(donorCattle);
        for (OocyteCollection collection : oocyteCollections) {
            collection.setDonorCattle(null);
            oocyteCollectionRepository.save(collection);
        }

        donorCattleRepository.deleteById(id);
    }

    public DonorCattleDto edit(Long id, DonorCattleDto dto){
        DonorCattle donorCattle = donorCattleRepository.findById(id)
                .orElseThrow(DonorCattleNotFoundException::new);

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
