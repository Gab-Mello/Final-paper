package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationSummaryResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public record FivResponseDto(Long id, FivStatusEnum status,
                             LocalDate date, String farm,
                             String laboratory, String client,
                             String veterinarian, String technical,
                             String TE, List<OocyteCollectionResponseDto> oocyteCollections,
                             CultivationSummaryResponseDto cultivation) {


    public static FivResponseDto toFivResponseDto(Fiv fiv){
        return new FivResponseDto(
                fiv.getId(),
                fiv.getStatus(),
                fiv.getDate(),
                fiv.getFarm(),
                fiv.getLaboratory(),
                fiv.getClient(),
                fiv.getVeterinarian(),
                fiv.getTechnical(),
                fiv.getTE(),
                OocyteCollectionResponseDto.toOocyteCollectionDtoList(fiv.getOocyteCollections()),
                CultivationSummaryResponseDto.toCultivationSummaryDto(fiv.getCultivation()));
    }

    public static List<FivResponseDto> toFivResponseDtoList(List<Fiv> list){
        List<FivResponseDto> listDto = list.stream().map(fiv -> toFivResponseDto(fiv)).toList();
        return listDto;
    }
}
