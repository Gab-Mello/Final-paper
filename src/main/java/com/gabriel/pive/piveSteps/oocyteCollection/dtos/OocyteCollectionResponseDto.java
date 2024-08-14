package com.gabriel.pive.piveSteps.oocyteCollection.dtos;

import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionResponseDto(Long donorCattleId,
                                          Long bullId,
                                          String technical,
                                          Integer viableOocytes,
                                          Integer nonViableOocytes,
                                          Integer totalOocytes,
                                          LocalDate date) {



    public static OocyteCollectionResponseDto toOocyteCollectionDto(OocyteCollection oocyteCollection){
        return new OocyteCollectionResponseDto(oocyteCollection.getDonorCattle().getId(),
                oocyteCollection.getBull().getId(),
                oocyteCollection.getTechnical(),
                oocyteCollection.getViableOocytes(),
                oocyteCollection.getNonViableOocytes(),
                oocyteCollection.getTotalOocytes(),
                oocyteCollection.getDate());
    }

    public static List<OocyteCollectionResponseDto> toOocyteCollectionDtoList(List<OocyteCollection> list){
        List<OocyteCollectionResponseDto> listDto = list.stream().map(collection -> toOocyteCollectionDto(collection)).toList();
        return listDto;
    }


}
