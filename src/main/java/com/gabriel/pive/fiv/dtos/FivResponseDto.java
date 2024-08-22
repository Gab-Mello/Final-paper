package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.fiv.cultivation.dtos.CultivationSummaryResponseDto;
import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.dtos.OocyteCollectionResponseDto;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public record FivResponseDto(Long id, OocyteCollectionResponseDto oocyteCollection,
                             CultivationSummaryResponseDto cultivation) {


    public static FivResponseDto toFivResponseDto(Fiv fiv){
        return new FivResponseDto(
                fiv.getId(),
                OocyteCollectionResponseDto.toOocyteCollectionDto(fiv.getOocyteCollection()),
                CultivationSummaryResponseDto.toCultivationSummaryDto(fiv.getCultivation()));
    }

    public static List<FivResponseDto> toFivResponseDtoList(List<Fiv> list){
        List<FivResponseDto> listDto = list.stream().map(fiv -> toFivResponseDto(fiv)).toList();
        return listDto;
    }
}
