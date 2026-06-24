package com.gabriel.pive.fiv.oocytecollection.services;

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
import com.gabriel.pive.fiv.oocytecollection.dtos.OocyteCollectionRequestDto;
import com.gabriel.pive.fiv.oocytecollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocytecollection.exceptions.DonorAlreadyCollectedException;
import com.gabriel.pive.fiv.oocytecollection.exceptions.FivAlreadyHasOocyteCollectionException;
import com.gabriel.pive.fiv.oocytecollection.exceptions.OocyteCollectionNotFoundException;
import com.gabriel.pive.fiv.oocytecollection.repositories.OocyteCollectionRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OocyteCollectionService {

    private final OocyteCollectionRepository collectionRepository;
    private final DonorCattleRepository donorCattleRepository;
    private final BullRepository bullRepository;
    private final FivRepository fivRepository;
    private final FivService fivService;
    private final DonorCattleService donorCattleService;

    @Transactional
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
