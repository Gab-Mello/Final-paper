package com.gabriel.pive.piveSteps.oocyteCollection.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionRequestDto(Long donorCattleId,
                                         Long bullId,
                                         String technical,
                                         Integer viableOocytes,
                                         Integer nonViableOocytes,
                                         LocalDate date) {

    public OocyteCollection toOocyteCollection(DonorCattle donorCattle, Bull bull){
        return new OocyteCollection(
                donorCattle,
                bull,
                technical,
                viableOocytes,
                nonViableOocytes,
                viableOocytes + nonViableOocytes,
                date);
    }


}
