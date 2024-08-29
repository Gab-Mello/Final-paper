package com.gabriel.pive.fiv.cultivation.dtos;


import com.gabriel.pive.fiv.cultivation.entities.Cultivation;
import com.gabriel.pive.fiv.entities.Fiv;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CultivationRequestDto(Long fivId,
                                    @NotNull(message = "Número total de embriões em branco.") Integer totalEmbryos,
                                    @NotNull(message = "Número de embriões viáveis em branco.") Integer viableEmbryos) {

    public Cultivation toCultivation(Fiv fiv){
        return new Cultivation(
                fiv,
                totalEmbryos,
                viableEmbryos);
    }


}
