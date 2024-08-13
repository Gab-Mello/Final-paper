package com.gabriel.pive.piveSteps.oocyteCollection.dtos;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionDto(Long donorCattleId,
                                  Long bullId,
                                  Integer totalOocytes,
                                  LocalDate date) {

    public OocyteCollection toOocyteCollection(DonorCattle donorCattle, Bull bull){
        return new OocyteCollection(
                donorCattle,
                bull,
                totalOocytes,
                date);
    }

    public static OocyteCollectionDto toOocyteCollectionDto(OocyteCollection oocyteCollection){
        return new OocyteCollectionDto(oocyteCollection.getDonorCattle().getId(),
                oocyteCollection.getBull().getId(),
                oocyteCollection.getTotalOocytes(),
                oocyteCollection.getDate());
    }

    public static List<OocyteCollectionDto> toOocyteCollectionDtoList(List<OocyteCollection> list){
        List<OocyteCollectionDto> listDto = list.stream().map(collection -> toOocyteCollectionDto(collection)).toList();
        return listDto;
    }

}
