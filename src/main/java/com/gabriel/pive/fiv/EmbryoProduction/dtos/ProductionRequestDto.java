package com.gabriel.pive.fiv.EmbryoProduction.dtos;


import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProductionRequestDto(@NotNull(message = "Id da coleta em branco.") Long oocyteCollectionId,
                                   @NotNull(message = "Número total de embriões em branco.")
                                   @Positive(message = "Número total de embriões deve ser positivo.")
                                   Integer totalEmbryos
                                    ) {

    public EmbryoProduction toProduction(OocyteCollection oocyteCollection){
        return new EmbryoProduction(
                oocyteCollection,
                totalEmbryos);
    }


}
