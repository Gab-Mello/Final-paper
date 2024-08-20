package com.gabriel.pive.fiv.oocyteCollection.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionPostDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OocyteCollectionService {

    @Autowired
    private OocyteCollectionRepository collectionRepository;

    @Autowired
    private DonorCattleRepository donorCattleRepository;

    @Autowired
    private BullRepository bullRepository;

    @Autowired
    private FivRepository fivRepository;

    public OocyteCollectionPostDto newOocyteCollection(OocyteCollectionRequestDto dto){
        DonorCattle donorCattle = donorCattleRepository.findById(dto.donorCattleId())
                .orElseThrow(() -> new EntityNotFoundException("Donor not found"));

        Bull bull = bullRepository.findById(dto.bullId())
                .orElseThrow(() -> new EntityNotFoundException("Bull not found"));

        Fiv fiv = fivRepository.findById(dto.fivId())
                .orElseThrow(() -> new EntityNotFoundException("Fiv not found"));

        OocyteCollection oocyteCollection = dto.toOocyteCollection(donorCattle, bull);

        OocyteCollection savedOocyteCollection = collectionRepository.save(oocyteCollection);

        fiv.setOocyteCollection(savedOocyteCollection);
        fivRepository.save(fiv);

        return OocyteCollectionPostDto.toOocyteCollectionPostDto(savedOocyteCollection, fiv.getId());

    }

    public List<OocyteCollectionResponseDto> getAll(){
        List<OocyteCollection> list = collectionRepository.findAll();
        return OocyteCollectionResponseDto.toOocyteCollectionDtoList(list);
    }

    public OocyteCollectionResponseDto editCollection(Long id, OocyteCollectionRequestDto dto){
        OocyteCollection oocyteCollection = collectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oocyte Collection not found"));

        DonorCattle donorCattle = donorCattleRepository.findById(dto.donorCattleId())
                .orElseThrow(() -> new EntityNotFoundException("Donor not found"));

        Bull bull = bullRepository.findById(dto.bullId())
                .orElseThrow(() -> new EntityNotFoundException("Bull not found"));

        return OocyteCollectionResponseDto.toOocyteCollectionDto(collectionRepository.save(OocyteCollectionResponseDto.
                editMapper(oocyteCollection, dto, donorCattle, bull)));

    }

    public OocyteCollectionResponseDto getCollectionById(Long id){

        OocyteCollection oocyteCollection = collectionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Oocyte Collection not found"));

        return OocyteCollectionResponseDto.toOocyteCollectionDto(oocyteCollection);
    }

    public List<OocyteCollectionResponseDto> filterByBull(String registrationNumber){
        List<OocyteCollection> list = collectionRepository.findByBullRegistrationNumber(registrationNumber);
        return OocyteCollectionResponseDto.toOocyteCollectionDtoList(list);
    }

    public List<OocyteCollectionResponseDto> filterByDonor(String registrationNumber){
        List<OocyteCollection> list = collectionRepository.findByDonorCattleRegistrationNumber(registrationNumber);
        return OocyteCollectionResponseDto.toOocyteCollectionDtoList(list);
    }



}
