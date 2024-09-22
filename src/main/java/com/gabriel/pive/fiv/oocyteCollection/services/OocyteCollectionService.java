package com.gabriel.pive.fiv.oocyteCollection.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.BullNotFoundException;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.animals.services.DonorCattleService;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionPostDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.DonorAlreadyCollectedException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.FivAlreadyHasOocyteCollectionException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.OocyteCollectionNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.ViableOocytesBiggerThanTotalException;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Hibernate;
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

    @Autowired
    private FivService fivService;

    @Autowired
    private DonorCattleService donorCattleService;

    public OocyteCollectionResponseDto newOocyteCollection(OocyteCollectionRequestDto dto){
        Fiv fiv = fivRepository.findById(dto.fivId())
                .orElseThrow(FivNotFoundException::new);

        DonorCattle donorCattle = donorCattleRepository.findById(dto.donorCattleId())
                .orElseThrow(DonorCattleNotFoundException::new);


        Bull bull = bullRepository.findById(dto.bullId())
                .orElseThrow(BullNotFoundException::new);

        if (fivRepository.existsByOocyteCollections_DonorCattleAndId(donorCattle, fiv.getId())){
            throw new DonorAlreadyCollectedException();
        }

        if (dto.viableOocytes() > dto.totalOocytes()){
            throw new ViableOocytesBiggerThanTotalException();
        }

        fivService.updateTotalOocytes(fiv, dto.totalOocytes());
        fivService.updateTotalViableOocytes(fiv, dto.viableOocytes());


        OocyteCollection oocyteCollection = dto.toOocyteCollection(fiv, donorCattle, bull);
        donorCattle.getOocyteCollections().add(oocyteCollection);
        collectionRepository.save(oocyteCollection);

        donorCattleService.updateAverageViableOocytes(donorCattle);


        if (dto.finished()){
            fiv.setStatus(FivStatusEnum.OOCYTE_COLLECTION_COMPLETED);
            fivRepository.save(fiv);
        }

        return OocyteCollectionResponseDto.toOocyteCollectionDto(oocyteCollection);

    }

    public List<OocyteCollectionResponseDto> getAll(){
        List<OocyteCollection> list = collectionRepository.findAll();
        return OocyteCollectionResponseDto.toOocyteCollectionDtoList(list);
    }

//    public OocyteCollectionResponseDto editCollection(Long id, OocyteCollectionRequestDto dto){
//        OocyteCollection oocyteCollection = collectionRepository.findById(id)
//                .orElseThrow(OocyteCollectionNotFoundException::new);
//
//        DonorCattle donorCattle = donorCattleRepository.findById(dto.donorCattleId())
//                .orElseThrow(DonorCattleNotFoundException::new);
//
//        Bull bull = bullRepository.findById(dto.bullId())
//                .orElseThrow(BullNotFoundException::new);
//
//        return OocyteCollectionResponseDto.toOocyteCollectionDto(collectionRepository.save(OocyteCollectionResponseDto.
//                editMapper(oocyteCollection, dto, donorCattle, bull)));
//
//    }

    public OocyteCollectionResponseDto getCollectionById(Long id){

        OocyteCollection oocyteCollection = collectionRepository.findById(id)
                .orElseThrow(OocyteCollectionNotFoundException::new);

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
