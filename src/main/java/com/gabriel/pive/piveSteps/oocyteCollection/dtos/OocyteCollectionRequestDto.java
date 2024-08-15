package com.gabriel.pive.piveSteps.oocyteCollection.dtos;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.piveSteps.oocyteCollection.entities.OocyteCollection;

import java.time.LocalDate;
import java.util.List;

public record OocyteCollectionRequestDto( LocalDate date,
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
