package com.gabriel.pive.fiv.oocyteCollection.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;

public record OocyteCollectionRequestDto( Long fivId,
                                          LocalDate date,
                                          String farm,
                                          String laboratory,
                                          String client,
                                          String veterinarian,
                                          String technical,
                                          Long donorCattleId,
                                          Long bullId,
                                          Integer totalOocytes,
                                          Integer viableOocytes
                                         ) {

    public OocyteCollection toOocyteCollection(DonorCattle donorCattle, Bull bull){
        return new OocyteCollection(
                donorCattle,
                bull,
                farm,
                laboratory,
                client,
                veterinarian,
                technical,
                totalOocytes,
                viableOocytes,
                totalOocytes - viableOocytes,
                date);
    }


}
