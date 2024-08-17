package com.gabriel.pive.fiv.dtos;

import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;

public record FivResponseDto(Long id, OocyteCollection oocyteCollection, Cultivation cultivation) {

    public static FivResponseDto toFivResponseDto(Fiv fiv){
        return new FivResponseDto(fiv.getId(), fiv.getOocyteCollection(), fiv.getCultivation());
    }
}
