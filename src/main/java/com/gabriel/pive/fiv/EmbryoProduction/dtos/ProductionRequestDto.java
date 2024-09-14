package com.gabriel.pive.fiv.EmbryoProduction.dtos;


import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import jakarta.validation.constraints.NotNull;


public record ProductionRequestDto(Long oocyteCollectionId,
                                   @NotNull(message = "Número total de embriões em branco.") Integer totalEmbryos
                                    ) {

    public EmbryoProduction toProduction(OocyteCollection oocyteCollection){
        return new EmbryoProduction(
                oocyteCollection,
                totalEmbryos);
    }


}
