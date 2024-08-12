package com.gabriel.pive.piveSteps.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.entities.OocyteCollection;

import java.time.LocalDate;

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

}
