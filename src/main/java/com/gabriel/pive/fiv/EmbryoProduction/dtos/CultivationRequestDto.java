package com.gabriel.pive.fiv.EmbryoProduction.dtos;


import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.validation.constraints.NotNull;


public record CultivationRequestDto(Long fivId,
                                    @NotNull(message = "Número total de embriões em branco.") Integer totalEmbryos,
                                    @NotNull(message = "Número de embriões viáveis em branco.") Integer viableEmbryos) {

    public EmbryoProduction toCultivation(Fiv fiv){
        return new EmbryoProduction(
                fiv,
                totalEmbryos,
                viableEmbryos);
    }


}
