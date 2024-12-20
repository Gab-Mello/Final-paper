package com.gabriel.pive.fiv.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.exceptions.InvalidDateException;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.InvalidNumberOfEmbryosException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.services.ProductionService;
import com.gabriel.pive.fiv.dtos.FivRequestDto;
import com.gabriel.pive.fiv.dtos.FivResponseDto;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FivService {

    @Autowired
    private FivRepository fivRepository;

    @Autowired
    private ProductionRepository productionRepository;

    public FivResponseDto createFiv(FivRequestDto dto){
        Fiv fiv = dto.toFiv();
        fiv.setStatus(FivStatusEnum.IN_PROCESS);
        return FivResponseDto.toFivResponseDto(fivRepository.save(fiv));
    }

    public void updateTotalOocytes(Fiv fiv, Integer oocytes){
        fiv.updateTotalOocytesCollected(oocytes);
        fivRepository.save(fiv);
    }

    public void updateTotalViableOocytes(Fiv fiv, Integer oocytes){
        fiv.updateTotalViableOocytesCollected(oocytes);
        fivRepository.save(fiv);
    }

    public void updateTotalEmbryos(Fiv fiv, Integer numberEmbryos){
        fiv.setTotalEmbryos(fiv.getTotalEmbryos() + numberEmbryos);
        Float embryosPercentage =  ((float)fiv.getTotalEmbryos() / fiv.getTotalViableOocytesCollected()) * 100;
        fiv.setEmbryosPercentage(embryosPercentage);
        fivRepository.save(fiv);
    }

    public void updateEmbryosRegistered(Fiv fiv, EmbryoProduction production){
        fiv.setEmbryosRegistered(fiv.getEmbryosRegistered() + 1);
        production.setEmbryosRegistered(production.getEmbryosRegistered() + 1);
        productionRepository.save(production);
        fivRepository.save(fiv);
    }

    public void updateFivWithFrozenOrDiscardedEmbryos(EmbryoProduction production, Integer number){

        Integer actualNumberOfEmbryos = production.getEmbryos().size();
        Integer updatedNumberOfEmbryos = actualNumberOfEmbryos + number;

        Fiv fiv = production.getOocyteCollection().getFiv();
        fiv.setEmbryosRegistered(fiv.getEmbryosRegistered() + number);

        production.setEmbryosRegistered(production.getEmbryosRegistered() + number);

        if (updatedNumberOfEmbryos > production.getTotalEmbryos()
                || fiv.getEmbryosRegistered() > fiv.getTotalEmbryos()){
            throw new InvalidNumberOfEmbryosException();
        }

        if (fiv.getEmbryosRegistered() == fiv.getTotalEmbryos()){
            fiv.setStatus(FivStatusEnum.COMPLETED);
        }

        productionRepository.save(production);
        fivRepository.save(fiv);
    }

    public void checkToSetFivAsCompleted(Fiv fiv){
        if (fiv.getEmbryosRegistered() == fiv.getTotalEmbryos() - 1){
            fiv.setStatus(FivStatusEnum.COMPLETED);
            fivRepository.save(fiv);
        }
    }

    public List<FivResponseDto> getAllFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findAllByOrderByIdDesc());
    }

    public FivResponseDto getFivById(Long id){
        Fiv fiv = fivRepository.findById(id).
            orElseThrow(FivNotFoundException::new);

        return FivResponseDto.toFivResponseDto(fiv);
    }

    public List<FivResponseDto> getInProcessFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatusOrderByIdDesc(FivStatusEnum.IN_PROCESS));
    }

    public List<FivResponseDto> getOocyteCollectionCompletedFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatusOrderByIdDesc(FivStatusEnum.OOCYTE_COLLECTION_COMPLETED));
    }

    public List<FivResponseDto> getCompletedFives(){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByStatusOrderByIdDesc(FivStatusEnum.COMPLETED));
    }

    public List<FivResponseDto> filterFivesByBull(Long bullId){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByOocyteCollections_Bull_IdOrderByIdDesc(bullId));
    }

    public List<FivResponseDto> filterFivesByDonor(Long donorId){
        return FivResponseDto.toFivResponseDtoList(fivRepository.findByOocyteCollections_DonorCattle_IdOrderByIdDesc(donorId));
    }

}
